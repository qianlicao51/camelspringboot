package com.study.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/22 16:13
 */
@Slf4j(topic = "c.Test4")
public class Test4 {

    private static void test3() throws InterruptedException {
        final Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park();
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
            //打断标志为true时，park不生效
            LockSupport.park();
            log.debug("unpark...");
        }, "t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        test3();
    }
}
