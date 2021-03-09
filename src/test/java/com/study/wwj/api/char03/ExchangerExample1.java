package com.study.wwj.api.char03;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/9 11:13
 */
public class ExchangerExample1 {
    public static void main(String[] args) {
        //定义exchange类，String类型 标明一对线程交换的数据只能是String
        final Exchanger<String> exchanger = new Exchanger<>();
        //定义线程1
        new Thread(() -> {
            System.out.println(Thread.currentThread() + " start.");
            //随机睡眠
            randomSleep();
            //① 执行exchange 方法，将对应的数据传递给线程T2，同时从T2线程获取交换的数据
            try {
                final String data = exchanger.exchange("T am from T1");
                System.out.println(Thread.currentThread() + " received ->" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " end.");

        }, "T1").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread() + " start.");
            //随机睡眠
            randomSleep();
            //① 执行exchange 方法，将对应的数据传递给线程T2，同时从T2线程获取交换的数据
            try {
                final String data = exchanger.exchange("T am from T2");
                System.out.println(Thread.currentThread() + " received ->" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " end.");

        }, "T2").start();
    }

    private static void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
        }
    }
}
