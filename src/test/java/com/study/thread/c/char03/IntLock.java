package com.study.thread.c.char03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/9 10:03
 */
public class IntLock implements Runnable {

    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    int lock;

    /**
     * 控制加锁顺序 ，方便构造死锁
     *
     * @param lock
     */
    public IntLock(int lock) {
        this.lock = lock;
    }

    public static void main(String[] args) throws InterruptedException {
        final IntLock r1 = new IntLock(1);
        final IntLock r2 = new IntLock(2);

        final Thread t1 = new Thread(r1);
        final Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
        TimeUnit.MILLISECONDS.sleep(500);
        //中断其中一个
        t2.interrupt();
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lock1.lockInterruptibly();
                TimeUnit.SECONDS.sleep(1);
                lock2.lockInterruptibly();
            } else {
                lock2.lockInterruptibly();
                TimeUnit.SECONDS.sleep(2);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(Thread.currentThread().getName() + ":线程中断");
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + ":线程退出");
        }
    }
}
