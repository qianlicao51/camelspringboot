package com.study.thread.c.char03;
/**
 * @author study
 * @version 1.0
 * @date 2021/2/9 11:01
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemapDemo implements Runnable {
    //构造方法 Boolean是为了表示是否是公平锁
    final Semaphore semp = new Semaphore(5, true);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);

        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }

    @Override
    public void run() {
        try {
            semp.acquire();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getId() + " is Done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semp.release();
        }
    }
}
