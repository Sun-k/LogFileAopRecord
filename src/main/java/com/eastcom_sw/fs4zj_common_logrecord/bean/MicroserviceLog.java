package com.eastcom_sw.fs4zj_common_logrecord.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Sunk
 * @create 2020-4-23-18:27
 **/
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "MICROSERVICE_LOG")
public class MicroserviceLog {


    //主键ID
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    //主帐号（4A帐号）
    @Column(name = "main_account")
    private String main_account;

    //从帐号（用户名）
    @Column(name = "follow_account")
    private String follow_account;

    //url地址    @Column(name = "request_url")
    private String request_url;

    //操作状态（成功、失败）
    @Column(name = "response_status")
    private String response_status;

    //操作内容（业务描述+操作参数）
    @Column(name = "request_content")
    private String request_content;

    //响应时延（ms）
    @Column(name = "response_delay")
    private Long response_delay;

    //请求时间
    @Column(name = "request_date")
    private Date request_date;

    //请求服务器IP
    @Column(name = "request_host_ip")
    private String request_host_ip;

    //响应服务器IP
    @Column(name = "response_host_ip")
    private String response_host_ip;

    //备注
    @Column(name = "remark")
    private String remark;

    //应用服务归属
    @Column(name = "app_type")
    private String app_type;


    public MicroserviceLog(){}

    @Override
    public String toString() {
        return super.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain_account() {
        return main_account;
    }

    public void setMain_account(String main_account) {
        this.main_account = main_account;
    }

    public String getFollow_account() {
        return follow_account;
    }

    public void setFollow_account(String follow_account) {
        this.follow_account = follow_account;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public String getRequest_content() {
        return request_content;
    }

    public void setRequest_content(String request_content) {
        this.request_content = request_content;
    }

    public Long getResponse_delay() {
        return response_delay;
    }

    public void setResponse_delay(Long response_delay) {
        this.response_delay = response_delay;
    }

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public String getRequest_host_ip() {
        return request_host_ip;
    }

    public void setRequest_host_ip(String request_host_ip) {
        this.request_host_ip = request_host_ip;
    }

    public String getResponse_host_ip() {
        return response_host_ip;
    }

    public void setResponse_host_ip(String response_host_ip) {
        this.response_host_ip = response_host_ip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }
}
