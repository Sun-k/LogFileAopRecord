package com.eastcom_sw.fs4zj_common_logrecord.log;

import com.eastcom_sw.firebrand.common.utils.OwnUtil;
import com.eastcom_sw.fs4zj_common_logrecord.bean.MicroserviceLog;
import com.eastcom_sw.fs4zj_common_logrecord.dao.MicroserviceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听日志事件
 *
 * @author Sunk
 * @create 2020-4-24-9:41
 **/
@Component
public class SystemLogListener {

    @Autowired
    private MicroserviceJpaRepository microserviceJpaRepository;

    @Async
    @Order
    @EventListener(SystemLogEvent.class)
    public void saveOperateRecord(SystemLogEvent event) {
        MicroserviceLog microserviceLog = (MicroserviceLog) event.getSource();
        if(null == microserviceLog.getId()){
            microserviceLog.setId(OwnUtil.getUUID());
        }
        //保存日志
        microserviceJpaRepository.save(microserviceLog);
    }
}
