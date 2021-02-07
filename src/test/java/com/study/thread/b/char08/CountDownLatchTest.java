package com.study.thread.b.char08;

import java.util.concurrent.CountDownLatch;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/7 21:42
 */
public class CountDownLatchTest {

    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
        }).start();

        //不要写成 wait()
        c.await();
        System.out.println(3);
    }
}
