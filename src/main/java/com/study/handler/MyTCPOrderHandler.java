package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/14 15:51
 */
@Component("myTCPOrderHandler")
public class MyTCPOrderHandler {
    @Handler
    public synchronized void handler(Exchange exchange) {
        Message in = exchange.getIn();
        String headbody = in.getBody(String.class);
        // System.out.println("接受到的数据：" + headbody);


        System.out.println(String.format("%s 线程是%s", "MyTCPOrderHandler", Thread.currentThread().getName()));
        System.out.println(StringUtils.repeat("--", "*", 30));
        System.out.println("接受到的数据：" + headbody);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + headbody);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        // return "Order: OK";

        List<String> data = new ArrayList<>();

        data.add(Thread.currentThread().getName());

        in.setBody(System.currentTimeMillis() + headbody + Thread.currentThread().getName());
    }


}
