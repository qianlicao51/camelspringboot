package com.study.wwj.char07;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/7 10:04
 */
public class ThreadHook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The hook thread 1 is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread will exit.");
        }, "Sys-Hook"));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The hook thread 2 is running.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The hook thread 2 will exit.");
        }, "Sys-Hook-2"));
        System.out.println("The program will is stopping.");
    }
}
