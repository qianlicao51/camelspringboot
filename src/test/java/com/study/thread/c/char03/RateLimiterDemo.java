package com.study.thread.c.char03;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/9 11:33
 */

import com.google.common.util.concurrent.RateLimiter;
import org.joda.time.DateTime;

import java.util.concurrent.Executors;

public class RateLimiterDemo {
    public static final String FORMAT_Date = "yyyy-MM-dd HH:mm:ss:SSS";
    //限制每秒处理2个请求
    static RateLimiter limiter = RateLimiter.create(2);

    public static void main(String[] args) {
        Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            //控制流量
            // limiter.acquire();

            //在某些场景中，如果系统无法处理请求，为了保证服务质量，
            //更倾向于直接丢弃过载请求，从而避免可能的奔溃
            if (!limiter.tryAcquire()) {
                continue;
            }
            new Thread(new Task()).start();
        }
    }

    public static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println(new DateTime().toString(FORMAT_Date));
        }
    }
}
