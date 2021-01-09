package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 11:05
 * 多线程处理 测试 业务重复打断
 */
@Component("threadMessageHandler")
public class ThreadMessageHandler implements MessageHandler {
    public static final ConcurrentHashMap<String, Thread> MAP = new ConcurrentHashMap<>();

    /**
     * @param exchange 消息
     */
    @Override
    public void handler(Exchange exchange) {

        Message in = exchange.getIn();
        String body = in.getBody(String.class);
        final Thread currentThread = Thread.currentThread();

        System.out.println();
        System.out.println(String.format("接受到的数据：%s 处理的线程是%s", body, currentThread));
        System.out.println(StringUtils.repeat("--", "*", 30));
        if (MAP.containsKey(body)) {
            final Thread thread = MAP.get(body);
            System.out.println(String.format("发生重复;key  %s  的线程%s 将被打断", body, thread));
            thread.interrupt();
        }
        MAP.put(body, currentThread);
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(String.format("%s %s 线程是%s", body, i, currentThread.getName()));
            } catch (InterruptedException e) {
                System.out.println(MAP.get(body) + "--线程接收到被打断信号");
                //此处直接反复，否则会进入下面 移除 key，导致业务还会发生重复
                return;
            }
        }
        //正常结束后 才移除。否则打断也移除，业务还会重复
        MAP.remove(body);
        in.setBody(System.currentTimeMillis() + body + currentThread.getName());
    }
}
