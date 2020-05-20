package com.eastcom_sw.fs4zj_common_logrecord.aspect;

import com.eastcom_sw.firebrand.common.exception.BaseException;
import com.eastcom_sw.fs4zj_common_logrecord.bean.Response;
import com.eastcom_sw.fs4zj_common_logrecord.bean.Result;
import com.eastcom_sw.fs4zj_common_logrecord.exception.ExceptionHandle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * http请求切面异常处理
 *
 * @author Sunk
 * @create 2020-5-09-15:34
 **/

@Aspect
@Component
public class HttpAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExceptionHandle exceptionHandle;

    @Pointcut("execution(* com.eastcom_sw.fs4zj_common_logrecord..*.*(..))")
    public void log(){}


    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        }
        catch (BaseException e){
            logger.error("dataAccessException:" + e.getMessage(),e);
            result =  new Response(false,e.getMessage());
        }
        catch (NoHandlerFoundException e){
            logger.error("NoHandlerFoundException:" + e.getMessage(),e);
            result =  new Response(false,e.getMessage());
        }
        catch (ServletException e){
            logger.error("ServletException:" + e.getMessage(),e);
            result =  new Response(false,e.getMessage());
        }
        catch (DataAccessException e){
            logger.error("dataAccessException:" + e.getMessage(),e);
            result =  new Response(false,e.getMostSpecificCause().toString());
        }catch (RuntimeException e){
            logger.error("RuntimeException:" + e.getMessage(),e);
            result =  new Response(false,e.getMessage());
        }
        catch (Exception e){
            logger.error("Exception:"+e.getMessage(),e);
            result =  new Response(false,e.getMessage());
        }

        return result;
    }

}
