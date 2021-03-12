package com.study.wwj.api.char03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/12 13:54
 */
public class ReentrantLockExample2 {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    private static void m1() {
        lock1.lock();
        System.out.println(Thread.currentThread() + " get lock1.");
        try {
            lock2.lock();
            System.out.println(Thread.currentThread() + " get lock2.");
            try {
            } catch (Exception e) {
            } finally {
                lock2.unlock();
                System.out.println(Thread.currentThread() + " release lock2.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
            System.out.println(Thread.currentThread() + " release lock1.");
        }
    }

    private static void m2() {
        lock2.lock();
        System.out.println(Thread.currentThread() + " get lock2.");
        try {
            lock1.lock();
            System.out.println(Thread.currentThread() + " get lock1.");
            try {
            } catch (Exception e) {
            } finally {
                lock1.unlock();
                System.out.println(Thread.currentThread() + " release lock1.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
            System.out.println(Thread.currentThread() + " release lock2.");
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                m1();
            }
        }, "").start();
    }
}
