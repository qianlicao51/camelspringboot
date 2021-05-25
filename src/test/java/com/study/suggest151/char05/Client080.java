package com.study.suggest151.char05;

import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 10:51
 */
public class Client080 {
    public static void main1(String[] args) {
        //.ConcurrentModificationException
        // final List<String> tickets = new ArrayList<>();

        //ConcurrentModificationException
        final List<String> tickets = new Vector<>();
        //初始化票据池
        for (int i = 0; i < 100_000; i++) {
            tickets.add("火车票" + i);
        }
        //退票
        final Thread returnThread = new Thread(() -> {
            while (true) {
                tickets.add("车票" + new Random().nextInt());
            }
        });
        //售票
        final Thread saleThread = new Thread(() -> {
            for (String ticket : tickets) {
                tickets.remove(ticket);
            }
        });
        //启动退票线程
        returnThread.start();
        //启动售票线程
        saleThread.start();
    }

    public static void main(String[] args) {
        final List<String> tickets = new Vector<>();
        //初始化票据池
        for (int i = 0; i < 100_000; i++) {
            tickets.add("火车票" + i);
        }
        //10个售票窗口
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "_" + tickets.remove(0));
                }
            }).start();
        }
    }
}
