package com.study.thread.c.char03;
/**
 * @author study
 * @version 1.0
 * @date 2021/2/9 11:15
 */

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        //等待检查
        end.await();
        System.out.println("Fire");
        exec.shutdown();
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
            System.out.println("check complete");
            end.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
