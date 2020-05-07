package com.eastcom_sw.fs4zj_common_logrecord.aspect;

import com.eastcom_sw.firebrand.common.utils.OwnUtil;
import com.eastcom_sw.fs4zj_common_logrecord.bean.MicroserviceLog;
import com.eastcom_sw.fs4zj_common_logrecord.bean.Response;
import com.eastcom_sw.fs4zj_common_logrecord.jwt.JwtTokenUtil;
import com.eastcom_sw.fs4zj_common_logrecord.log.SystemLogEvent;
import com.eastcom_sw.fs4zj_common_logrecord.note.LogRecord;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志切面
 *
 * @author Sunk
 * @create 2020-4-23-16:46
 **/
@Aspect
@Component
public class SystemLogAspect {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private MicroserviceLog microserviceLog = new MicroserviceLog();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private  GenericConversionService genericConversionService;

    // Controller层切点,针对在业务模块标注LogRecord注解记录日志
    @Pointcut("@annotation( com.eastcom_sw.fs4zj_common_logrecord.note.LogRecord )")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws Exception{

        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();

        // 请求的IP
        String userName = request.getParameter("UserName");
        microserviceLog = getControllerMethodDescription(joinPoint);
        microserviceLog.setRequest_host_ip(OwnUtil.getIpAddr(request));
        microserviceLog.setResponse_host_ip(OwnUtil.getSerIp());
        microserviceLog.setId(OwnUtil.getUUID());
        microserviceLog.setRequest_date(new Date());
        microserviceLog.setFollow_account(userName);
        microserviceLog.setMain_account(userName);
        microserviceLog.setRequest_url(request.getRequestURI());
    }

    /**
     * 返回通知
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) {
        //处理完请求，返回内容
        Response result = genericConversionService.convert(ret,Response.class);
        if (result.isCode()) {
            //正常返回
            microserviceLog.setResponse_status("成功");
        } else {
            microserviceLog.setResponse_status("失败");
            microserviceLog.setRemark(result.getMsg());
        }
        //发布事件
        applicationContext.publishEvent(new SystemLogEvent(microserviceLog));
    }

    @Around("controllerAspect()")
    public void around(){

        return;
    }

    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowable(Throwable e) {
        //异常
        microserviceLog.setResponse_status("失败");
        microserviceLog.setRemark(e.getMessage());
        //发布事件
        applicationContext.publishEvent(new SystemLogEvent(microserviceLog));
    }


    /**
     * 通过request对象获取用户名
     *
     * @param request
     * @return
     */
    private String getUsernameByRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(header) && header.startsWith("Bearer ")) {
//            String token = header.replace("Bearer ", "");
            return jwtTokenUtil.getUsernameFromToken(header);
        }
        return null;
    }


    /**
     * 获取方法上对应注解的value
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    private MicroserviceLog getControllerMethodDescription(JoinPoint joinPoint) throws Exception {

        LogRecord log;
        MicroserviceLog microserviceLog = new MicroserviceLog();
        //获取连接点目标类型
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取连接点签名的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取连接点参数
        Object[] arguments = joinPoint.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    log = method.getAnnotation(LogRecord.class);
                    microserviceLog.setApp_type(log.LogAppType());
                    microserviceLog.setRemark(log.remark());
                    break;
                }
            }
        }
        return microserviceLog;
    }
}
