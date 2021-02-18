package com.study.wwj.Guava;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/16 11:37
 */
public class ElapsedExample {

    public static void main(String[] args) throws InterruptedException {
        process("12");
    }

    static void process(String orderNo) throws InterruptedException {
        final Stopwatch started = Stopwatch.createStarted();
        System.out.printf("start process the order %s\n", orderNo);
        TimeUnit.SECONDS.sleep(2);
        System.out.printf("The orderNo  %s process successful and elapsed %s  \n", orderNo, started.stop());
    }

    static void process2(String orderNo) throws InterruptedException {
        final long start = System.nanoTime();
        System.out.printf("start process the order %s\n", orderNo);
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("The orderNo  %s process successful and elapsed %d ns\n", orderNo, (System.nanoTime() - start));
    }

}
