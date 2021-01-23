package com.study.thread.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/23 16:37
 */
@Slf4j
class Room {
    private int value = 0;

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

    public int getValue() {
        return value;
    }
}

@Slf4j
public class SynDemo {
    public static void main(String[] args) throws InterruptedException {
        final Room room = new Room();
        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.increment();
            }
        }, "t1");
        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                room.decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(room.getValue());

    }
}
