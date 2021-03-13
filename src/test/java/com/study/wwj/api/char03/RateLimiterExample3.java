package com.study.wwj.api.char03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 23:12
 */
public class RateLimiterExample3 {
    private static final AtomicInteger data = new AtomicInteger(0);
    private static final RateLimiterBucket bucket = new RateLimiterBucket();

    public static void main(String[] args) {
        //启动10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    bucket.sumbitRequest(data.getAndIncrement());
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            }).start();
        }
        //启动10个线程 模拟匀速地对漏桶请求处理
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    bucket.handleRequest(System.out::println);
                }
            }).start();
        }
    }
}
