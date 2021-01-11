package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/14 15:51
 */
@Component("myTCPOrderHandlerOrginal")
public class MyTCPOrderHandlerOrginal implements MessageHandler {

    @Override
    public void handler(Exchange exchange) {
        Message in = exchange.getIn();
        String body = in.getBody(String.class);

        final Thread currentThread = Thread.currentThread();
        System.out.println(StringUtils.repeat("--", "*", 30));

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(body);
        System.out.println(StringUtils.repeat("--", "*", 30));
        in.setBody(System.currentTimeMillis() + body + currentThread.getName());
    }

}
