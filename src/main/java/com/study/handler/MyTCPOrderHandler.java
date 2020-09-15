package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/14 15:51
 */
@Component("myTCPOrderHandler")
public class MyTCPOrderHandler {
    @Handler
    public void handler(Exchange exchange) {
        Message in = exchange.getIn();
        String headbody = in.getBody(String.class);
        System.out.println("接受到的数据：" + headbody);
        // return "Order: OK";
        in.setBody(System.currentTimeMillis() + headbody);
    }
}
