package com.eastcom_sw.fs4zj_common_logrecord.exception;

import com.eastcom_sw.fs4zj_common_logrecord.bean.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常捕获
 * @author Sunk
 * @create 2020-5-11-16:03
 **/
@RestControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    /**
     * 在controller里面内容执行之前，
     * 校验一些参数不匹配；Get post方法不一致
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        if(ex instanceof MethodArgumentTypeMismatchException){
            return new ResponseEntity<Object>(new Response(false,"参数类型不匹配异常"), status);
        }

        if(ex instanceof IllegalArgumentException){
            return new ResponseEntity<Object>(new Response(false,"参数不合法异常"), status);
        }

        return new ResponseEntity<Object>(new Response(false,"异常："+ex.getMessage()), status);

    }

}
