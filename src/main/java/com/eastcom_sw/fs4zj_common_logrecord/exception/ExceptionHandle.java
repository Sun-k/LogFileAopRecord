package com.eastcom_sw.fs4zj_common_logrecord.exception;

import com.eastcom_sw.firebrand.common.enums.ResultCode;
import com.eastcom_sw.fs4zj_common_logrecord.bean.Response;
import com.eastcom_sw.fs4zj_common_logrecord.bean.Result;
import com.eastcom_sw.fs4zj_common_logrecord.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/*
*
 * 异常处理
 *
 * @author Sunk
 * @create 2020-5-09-14:51
 **/

@Component
public class ExceptionHandle {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionGet(Exception e){

        if(e instanceof DescribeException){
            DescribeException describeException = (DescribeException) e;
            return ResultUtil.error(describeException.getCode(),describeException.getMessage());
        }

        LOGGER.error("\n↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓【系统异常】↓↓↓↓↓↓↓↓↓↓↓\n",e);
        return ResultUtil.error(ResultCode.EXCEPTION);

    }
}
