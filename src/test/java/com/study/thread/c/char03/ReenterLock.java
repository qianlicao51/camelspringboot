package com.study.thread.c.char03;
/**
 * @author study
 * @version 1.0
 * @date 2021/2/9 9:52
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReenterLock implements Runnable {
    private static final ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final ReenterLock tl = new ReenterLock();
        final Thread t1 = new Thread(tl);
        final Thread t2 = new Thread(tl);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 1_000_000; j++) {
            lock.lock();
            try {
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
