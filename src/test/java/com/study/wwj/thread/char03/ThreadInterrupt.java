package com.study.wwj.thread.char03;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 10:18
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Oh , i am be interrupted.");
            }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
