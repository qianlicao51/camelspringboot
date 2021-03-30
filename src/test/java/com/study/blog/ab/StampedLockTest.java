package com.study.blog.ab;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/30 10:13
 */
//https://mp.weixin.qq.com/s/RfPrO2izZex52M0xVvrxVQ
//stampedLock CPU 问题
@Slf4j
public class StampedLockTest {
    public static void main(String[] args) throws InterruptedException {
        final StampedLock lock = new StampedLock();
        Thread t1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 模拟程序阻塞等待其他资源
            LockSupport.park();
            log.info(Thread.currentThread().getName());
        });
        t1.start();
        // 保证t1获取写锁
        Thread.sleep(100);
        Thread t2 = new Thread(() -> {
            // 阻塞在悲观读锁
            lock.readLock();
            log.info(Thread.currentThread().getName());
        });
        t2.start();
        // 保证t2阻塞在读锁
        Thread.sleep(100);
        // 中断线程t2,会导致线程t2所在CPU飙升
        t2.interrupt();
        t2.join();
    }
}
