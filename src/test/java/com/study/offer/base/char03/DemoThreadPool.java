package com.study.offer.base.char03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/4 21:54
 */
public class DemoThreadPool {
    public static void cachedPoolThread() {
        final ExecutorService threadPool = Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(5);


        //任务调度
        final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);

        //1 创建一个延迟3秒执行的线程
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds exec.");
            }
        }, 3, TimeUnit.SECONDS);

        //2 创建一个 延迟1 秒执行 且每 3秒执行一次的线程
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 second,repeat execute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);

    }

    public static void main(String[] args) {
        cachedPoolThread();
        Thread.currentThread().getState();
    }
}
