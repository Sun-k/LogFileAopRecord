package com.eastcom_sw.fs4zj_common_logrecord.bean;


import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Sunk
 * @create 2020-5-09-15:01
 **/
public class Result<T> {


    private Integer status;

    private String msg;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
