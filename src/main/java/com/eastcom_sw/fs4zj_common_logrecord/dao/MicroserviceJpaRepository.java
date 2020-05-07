package com.eastcom_sw.fs4zj_common_logrecord.dao;

import com.eastcom_sw.fs4zj_common_logrecord.bean.MicroserviceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Sunk
 * @create 2020-3-05-18:55
 **/
public interface MicroserviceJpaRepository extends JpaRepository<MicroserviceLog, String> {


    /**
     * 获取所有日志
     * @return
     */
    List<MicroserviceLog> findAll();

}
