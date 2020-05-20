package com.eastcom_sw.fs4zj_common_logrecord.exception;

import com.eastcom_sw.firebrand.common.enums.ResultCode;

/**
 * 错误描述
 *
 * @author Sunk
 * @create 2020-5-09-14:44
 **/

public class DescribeException extends RuntimeException {

    private Integer code;

/*
*
     * 集成exception 加入错误状态值
     * @param resultCode

*/

    public DescribeException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }

/**
     * 自定义错误信息
     * @param msg
     * @param code
     */

    public DescribeException(String msg,Integer code){
        super(msg);
        this.code=code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
