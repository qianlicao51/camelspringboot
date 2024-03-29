package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/14 15:51
 */
@Component("myTCPOrderHandler")
public class MyTCPOrderHandler {

    public static final ConcurrentHashMap<String, Thread> MAP = new ConcurrentHashMap<>();

    @Handler
    public void handler(Exchange exchange) {
        Message in = exchange.getIn();
        String body = in.getBody(String.class);
        final Thread currentThread = Thread.currentThread();
        System.out.println();
        System.out.println(String.format("接受到的数据：%s 处理的线程是%s", body, currentThread));
        System.out.println(StringUtils.repeat("--", "*", 30));
        for (Map.Entry<String, Thread> ma : MAP.entrySet()) {
            System.out.println(ma.getKey() + "<>" + ma.getValue());
        }
        //
        if (MAP.containsKey(body)) {
            System.out.println("重复");
            final Thread thread = MAP.get(body);
            System.out.println(String.format("key  %s  的线程%s将被打断", body, thread));
            thread.interrupt();
        }

        MAP.put(body, currentThread);
        for (int i = 0; i < 10; i++) {
            //真正打断的不是此处，是其他位置
            if (Thread.interrupted()) {
                System.out.println(MAP.get(body) + "线程接收到被打断信号");
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(String.format("%s %s 线程是%s", body, i, currentThread.getName()));
            } catch (InterruptedException e) {
                System.out.println(MAP.get(body) + "--线程接收到被打断信号");
                break;
            }
        }
        MAP.remove(body);
        in.setBody(System.currentTimeMillis() + body + currentThread.getName());
    }

}
