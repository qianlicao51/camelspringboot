package com.study.bookcurrent.char07.anndemo;

import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/3 15:31
 */
public class SyncThread implements Runnable {
    @GuardedBy("count")
    private volatile static int count;
    ArrayBlockingQueue s;

    public SyncThread() {
        count = 0;
    }

    public static void main(String[] args) {
        SyncThread syncThread = new SyncThread();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
    }

    public void run() {
        // synchronized (this) {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // }
    }

    public int getCount() {
        return count;
    }
}
