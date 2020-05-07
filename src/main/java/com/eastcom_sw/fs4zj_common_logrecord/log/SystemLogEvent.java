package com.eastcom_sw.fs4zj_common_logrecord.log;

import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author Sunk
 * @create 2020-4-24-9:37
 **/
public class SystemLogEvent extends ApplicationEvent {

    public SystemLogEvent(Object source){
        super(source);
    }
}
