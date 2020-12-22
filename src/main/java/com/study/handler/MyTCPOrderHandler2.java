package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2020/9/14 15:51
 */
@Component("myTCPOrderHandler2")
public class MyTCPOrderHandler2 {
    public static Long MonitoringTime = (long) 180000;
    /**
     * 线程隔离业务数据
     */
    public static final MapCache THREAD_ISOLATION = new MapCache();

    @Handler
    public void handler(Exchange exchange) {
        Message in = exchange.getIn();
        String receive = in.getBody(String.class);
        System.out.println("接受到的数据：" + receive);
        final boolean allow = THREAD_ISOLATION.get(receive, System.currentTimeMillis());
        if (!allow) {
            in.setBody("bus is allways running ....");
            return;
        }
        Long endTime = System.currentTimeMillis() + MonitoringTime;// 轮询结束时间
        for (; System.currentTimeMillis() < endTime; ) {
            System.out.println(String.format("%s|%s", Thread.currentThread().getName(), receive));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }
        THREAD_ISOLATION.del(receive);
        in.setBody(System.currentTimeMillis() + receive);
    }

    static class MapCache {

        public static final Map<String, Long> MAP = new HashMap<>();

        public void del(String str) {
            MAP.remove(str);
        }

        /**
         * 返回true表示 可以进行下一步
         *
         * @param str     key
         * @param endTime 上次线程结束时间
         * @return 返回true 表示可以进行下一步
         */
        public synchronized boolean get(String str, long endTime) {
            //1 不包含 说明可以直接进行下一步执行
            if (!MAP.containsKey(str)) {
                MAP.put(str, endTime);
                return true;
            }
            final Long strEndTime = MAP.get(str);
            return (strEndTime - System.currentTimeMillis()) < MonitoringTime / 3;
        }
    }
}
