package com.study.wwj.thread.char21;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 16:11
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        ThreadLocal<Integer> tlocal = new ThreadLocal<>();
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                try {
                    //每个线程都会设置爱 tlocal,但是彼此之间的数据是独立的
                    tlocal.set(i);
                    System.out.println(Thread.currentThread().getName() + " set i " + tlocal.get());
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " get i " + tlocal.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }
}
