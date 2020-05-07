package com.eastcom_sw.fs4zj_common_logrecord.test;

import com.eastcom_sw.fs4zj_common_logrecord.bean.Response;
import com.eastcom_sw.fs4zj_common_logrecord.note.LogRecord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author Sunk
 * @create 2020-4-24-14:37
 **/
@RestController
public class TestsSystemLogRecord {


    @LogRecord(LogAppType = "导出服务",remark = "导出服务")
    @RequestMapping(value = "/test" ,method = RequestMethod.GET)
    public Response test(){

        //
        Response response = new Response();
        response.setCode(true);
        response.setMsg("yes");
        int  i = 0;
        for(int j=0;j<100;j++){
            i+=j;
        }
        int l = 2/0;
        System.out.println(i);
        return response;
        //
    }
}
