package com.study.wwj.api.char04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 18:24
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        //生产者 消费者模式
        final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        //启动11个生产数据的线程，想队列尾写入数据
        IntStream.rangeClosed(0, 10).boxed()
                .map(i -> new Thread("P-Thread-" + i) {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                final String data = String.valueOf(System.currentTimeMillis());
                                queue.put(data);
                                System.out.println(Thread.currentThread().getName() + " produce data:" + data);
                                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                            } catch (InterruptedException e) {
                                System.out.println("reveived the interrupted SINGAL");
                                break;
                            }
                        }
                    }
                }).forEach(Thread::start);
        //定义11个消费者，从队列头部移除数据
        IntStream.rangeClosed(0, 10).boxed()
                .map(i -> new Thread("C-Thread-" + i) {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                final String data = queue.take();
                                System.out.println(Thread.currentThread().getName() + " consume data:" + data);
                                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
                            } catch (InterruptedException e) {
                                System.out.println("reveived the interrupted SINGAL");
                                break;
                            }
                        }
                    }
                }).forEach(Thread::start);
    }
}
