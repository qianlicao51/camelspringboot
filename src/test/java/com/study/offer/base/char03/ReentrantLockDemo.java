package com.study.offer.base.char03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 10:43
 */
public class ReentrantLockDemo implements Runnable {
    //step 1 定义一个 ReentrantLock
    private static final ReentrantLock lock = new ReentrantLock();
    private static int i = 1;

    public static void main(String[] args) throws InterruptedException {
        final ReentrantLockDemo lockDemo = new ReentrantLockDemo();
        final Thread thread = new Thread(lockDemo);
        thread.start();
        thread.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            //step 2 加锁
            lock.lock();
            // 可重入锁
            // lock.lock();
            try {
                i++;
            } finally {
                //step 3 释放锁
                lock.unlock();
                // 可重入锁
                // lock.unlock();
            }
        }
    }
}
