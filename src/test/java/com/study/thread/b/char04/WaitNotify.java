package com.study.thread.b.char04;

import com.study.utils.SysUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/6 14:46
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        final Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        final Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();

    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + " " + SysUtils.getDate());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " flag is false running " + SysUtils.getDate());
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock. notify" + SysUtils.getDate());
                lock.notifyAll();
                flag = false;
                SleepUtils.senond(5);
            }
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock again. sleep " + SysUtils.getDate());
                SleepUtils.senond(5);
            }
        }
    }

}
