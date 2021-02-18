package com.study.wwj.thread.char05;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 16:46
 */
public class SynchronizedDefect {
    public static void main(String[] args) throws InterruptedException {
        final SynchronizedDefect defect = new SynchronizedDefect();
        final Thread t1 = new Thread(defect::synMethdo, "T1");
        t1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        final Thread t2 = new Thread(defect::synMethdo, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(2);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
        System.out.println(t2.getState());
    }

    public synchronized void synMethdo() {
        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
