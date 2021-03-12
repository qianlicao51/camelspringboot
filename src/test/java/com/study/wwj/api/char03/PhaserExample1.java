package com.study.wwj.api.char03;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/12 10:25
 */
public class PhaserExample1 {
    public static void main(String[] args) throws InterruptedException {
        //①定义一个phaser ，并未指定分片数量parties，此时Phaser内部分片的数量parties 默认为0
        //后面可以通过register 方法动态增加
        final Phaser phaser = new Phaser();
        for (int i = 0; i < 10; i++) {
            //定义10个线程
            new Thread(() -> {
                //② 首先调用phaser的 register 方法使得phaser内部的parties加一
                phaser.register();
                try {
                    //采取随机休眠的方式模拟线程的运行开销
                    TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                    // ③ 线程结束 执行arrive方法
                    phaser.arrive();
                    System.out.println(new Date() + ":" + Thread.currentThread() + " completed the work.");
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }, "T" + i).start();
        }
        TimeUnit.SECONDS.sleep(5);
        //④ 主线程也调用注册方法，此时 parties的数量为 11
        phaser.register();
        // ⑤ 主线程也 arrive，但是它要等待下一个阶段，等待下一个阶段的前提是所有的线程都 arrive，也就是phaser 的 unarrived数量为0；
        phaser.arriveAndAwaitAdvance();
        assert phaser.getRegisteredParties() == 11 : "total 11 parties is registered ";
        System.out.println(new Date() + ": all of sub task completed work.");
    }
}
