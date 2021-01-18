package com.study.thinkinginjava.char21.part3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 22:40
 */
public class AttemptLocking {
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
        al.untimed();
        al.timed();

    }

    public void untimed() {
        final boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock " + captured);
        } finally {
            if (captured)
                lock.unlock();
        }
    }

    public void timed() {
        boolean captured = false;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2 TimeUnit.SECONDS)" + captured);
        } finally {
            if (captured) {
                lock.unlock();
            }
        }
    }

}
