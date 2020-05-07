package com.eastcom_sw.fs4zj_common_logrecord.note;

import java.lang.annotation.*;

/**
 * 日志记录注解
 *
 * @author Sunk
 * @create 2020-4-23-16:41
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LogRecord {

    String LogAppType()  default "";
    String remark()  default "";
}
