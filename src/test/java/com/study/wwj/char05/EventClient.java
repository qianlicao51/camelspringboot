package com.study.wwj.char05;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 15:47
 */
public class EventClient {
    public static void main(String[] args) {

        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        eventQueue.take();
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "Consumer-" + i).start();
        }
    }
}
