package com.study.wwj.api.char03;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/12 10:48
 */
public class PhaserExample2 {
    public static void main(String[] args) throws InterruptedException {
        //定义一个分片parties为0的Phaser
        final Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                //子线程调用注册方法，当子线程都执行了 都执行了register，parties将尾10
                phaser.register();
                try {
                    //采取随机休眠的方式模拟线程的运行开销
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    //调用 arriveAndWaitAdvance 方法等待所有线程 arrive，然后继续前行
                    phaser.arriveAndAwaitAdvance();
                    System.out.println(new Date() + ":" + Thread.currentThread() + " completed the work.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "T" + i).start();
        }
        //休眠以确保其子线程顺利调用register方法
        TimeUnit.SECONDS.sleep(5);
        //主线程调用 register方法，此时 pahser 内部的 parties为11
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println(new Date() + ": all of sub task completed work.");
    }
}
