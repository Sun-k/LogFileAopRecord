package com.eastcom_sw.fs4zj_common_logrecord.util;

import com.eastcom_sw.firebrand.common.enums.ResultCode;
import com.eastcom_sw.fs4zj_common_logrecord.bean.Result;

/**
 * @author Sunk
 * @create --09-15:22
 **/
public class ResultUtil {

    public static Result success(Object object){
        Result result = new Result();
        result.setStatus(0);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result success(){
        return success(null);
    }


    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setData(null);
        result.setMsg(msg);
        result.setStatus(code);
        return result;
    }

    public static Result error(ResultCode resultCode){
        Result result = new Result();
        result.setStatus(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        result.setData(null);
        return result;
    }



}
