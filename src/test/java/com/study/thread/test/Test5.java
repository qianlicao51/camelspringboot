package com.study.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/22 17:12
 */
@Slf4j(topic = "c.Test5")
public class Test5 {
    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("结束");
        }, "t1");
        t1.setDaemon(true);
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        log.debug("结束");
        //只要有一个线程还在运行就不会结束(非守护线程)
    }
}
