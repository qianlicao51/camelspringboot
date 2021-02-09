package com.study.thread.c.char03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/9 10:16
 */
public class TimeLock implements Runnable {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        final TimeLock tl = new TimeLock();
        final Thread t1 = new Thread(tl);
        final Thread t2 = new Thread(tl);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println("获得了锁:" + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(6);
            } else {
                System.out.println(Thread.currentThread().getName() + " get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                //没有获取锁，解锁会提示 IllegalMonitorStateException
                lock.unlock();
            }
        }

    }
}
