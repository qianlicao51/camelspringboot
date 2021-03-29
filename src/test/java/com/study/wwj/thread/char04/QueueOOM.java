package com.study.wwj.thread.char04;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/26 13:38
 */
public class QueueOOM {
    volatile static Queue<Object> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        queue.offer(new Object());
        Object item = new Object();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                long iterations = 0;
                try {
                    while (true) {
                        ++iterations;
                        queue.offer(item);
                        queue.remove(item);
                    }
                } catch (OutOfMemoryError e) {
                    queue = null;
                    System.err.println("iterations: " + iterations);
                    throw e;
                }
            }).start();
        }
    }
}
