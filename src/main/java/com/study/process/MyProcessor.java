package com.study.process;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.mina.MinaConstants;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/15 20:59
 */

/**
 * 一个切面
 */
@Component("myProcessor")
public class MyProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String body = in.getBody(String.class);
        // System.out.println("process receive" + body);
        exchange.getOut().setBody("Bye " + body);

        Map<String, Object> headers = in.getHeaders();
        headers.forEach((k, v) -> {
            //System.out.println(k + "|" + v);
        });

        System.out.println(String.format("%s 线程是%s", "MyProcessor", Thread.currentThread().getName()));
        //exchange.getOut().setHeader(MinaConstants.MINA_CLOSE_SESSION_WHEN_COMPLETE, true);
    }
}
