package com.study.offer.base.char03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 10:56
 */
public class InterruptiblyLock {
    //step 1 第一把锁1
    private static final ReentrantLock lock1 = new ReentrantLock();
    //step2 第二把锁2
    private static final ReentrantLock lock2 = new ReentrantLock();

    /**
     * 线程thread1 和线程thread2启动后，thread1先占用lock1,在占用lock2，thread2则先占用lock2，后占用lock1，
     * 这便形成了thread1和thread2之间的相互等待， 在两个线程都启动时便处于死锁状态，
     * 在while循环中，如果等待时间过长(这里设定了3s)，则可能发生了死锁等问题，
     * thread2就会中断中断(interrupt)，释放锁lock1的申请，同时释放以获得的lock2，让thread1顺利获得lock2，继续执行下去
     */
    public static void main(String[] args) {
        final long time = System.currentTimeMillis();
        final InterruptiblyLock interruptiblyLock = new InterruptiblyLock();
        final Thread thread1 = interruptiblyLock.lock1();
        final Thread thread2 = interruptiblyLock.lock2();
        //自旋一段时间，如果等待时间过长，则可能发生了死锁等问题，主动中断并释放锁
        while (true) {
            if (System.currentTimeMillis() - time >= 3000) {
                thread2.interrupt();//中断线程1
            }
        }
    }

    public Thread lock1() {
        final Thread t = new Thread(() -> {
            try {
                //step 3 如果当前线程未被中断，则获取锁
                lock1.lockInterruptibly();
                // step 4 执行具体业务逻辑
                TimeUnit.MILLISECONDS.sleep(500);

                lock2.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " 执行完毕！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //step 5 在业务逻辑执行结束后 检查当前线程是否持有该锁，如果持有则释放该锁
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " 退出");
            }
        });
        t.start();
        return t;
    }

    public Thread lock2() {
        final Thread t = new Thread(() -> {
            try {
                //step 3 如果当前线程未被中断，则获取锁
                lock2.lockInterruptibly();
                // step 4 执行具体业务逻辑
                TimeUnit.MILLISECONDS.sleep(500);

                lock1.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + " 执行完毕！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //step 5 在业务逻辑执行结束后 检查当前线程是否持有该锁，如果持有则释放该锁
                if (lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()) {
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " 退出");
            }
        });
        t.start();
        return t;
    }
}
