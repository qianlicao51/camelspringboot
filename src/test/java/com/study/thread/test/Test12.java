package com.study.thread.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/22 14:22
 */
@Slf4j(topic = "c.Test12")
public class Test12 {

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(() -> {
            while (true) {
                final Thread thread = Thread.currentThread();
                if (thread.isInterrupted()) {
                    break;
                }
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }
    /**
     * 使用线程stop方法真正杀死线程，如果这时现场锁住了共享资源，那么当它被杀死后就再也没有机会
     * 释放锁，其他线程将永远无法获取锁。
     */
}
