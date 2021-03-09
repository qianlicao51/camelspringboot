package com.study.offer.base.char03;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 22:01
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        // step 1 定义大小为2的CountDownLatch
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                System.out.println("子线程1正在执行");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("子线程1执行完毕");
                //step 2 在子线程调用countDown 方法
                latch.countDown();
            } catch (Exception e) {
            }
        }).start();
        new Thread(() -> {
            try {
                System.out.println("子线程2正在执行");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("子线程2执行完毕");
                //step 2 在子线程调用countDown 方法
                latch.countDown();
            } catch (Exception e) {
            }
        }).start();

        System.out.println("等待两个子线程执行完毕");
        try {
            //step 3 等待子线程执行完毕
            latch.await();
            // step 4 子线程执行完毕，开始执行主线程
            System.out.println("两个子线程执行完毕，继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
