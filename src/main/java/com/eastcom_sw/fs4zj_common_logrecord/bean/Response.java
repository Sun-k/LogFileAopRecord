package com.eastcom_sw.fs4zj_common_logrecord.bean;

import java.util.List;
import java.util.Map;

/**
 * 响应体
 *
 * @author Sunk
 * @create 2019-4-02-15:05
 **/
public class Response {

    private boolean code;
    private String msg;
    private List<Map<String,Object>> datas;

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Map<String,Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String,Object>> datas) {
        this.datas = datas;
    }
}
