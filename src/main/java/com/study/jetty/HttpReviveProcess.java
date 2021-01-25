package com.study.jetty;

import com.alibaba.fastjson.JSONObject;
import com.study.utils.ConfigUtils;
import com.study.utils.SysUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component("httpReviveProcess")
public class HttpReviveProcess implements Processor {

    @Override
    public void process(Exchange exchange) {
        Message in = exchange.getIn();
        //接收到的数据IP
	/*	Object header = in.getHeader("CamelMina2RemoteAddress");
		System.out.println(header.toString());*/
        Map<String, Object> headers = in.getHeaders();
        headers.forEach((k, v) -> {
            System.out.println(k + "|" + v);
        });

        ///////////////////

        String headbody = in.getBody(String.class);
        JSONObject obj = new JSONObject();
        try {
            obj.put(ConfigUtils.MSG_CODE, "0000");
            obj.put(ConfigUtils.MSG_NAME, "成功");
            obj.put("data", headbody);

        } catch (Exception e) {
            obj.put(ConfigUtils.MSG_CODE, "9000");
            obj.put(ConfigUtils.MSG_NAME, "失败");
            e.printStackTrace();
        } finally {
            System.out.println(SysUtils.getDate() + "|" + obj.toJSONString());
            in.setBody(headbody);
        }
    }
}
