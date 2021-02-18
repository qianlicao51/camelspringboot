package com.study.wwj.thread.char03;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 10:40
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        final List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(ThreadJoin::create).collect(Collectors.toList());
        //启动这两个线程
        threads.forEach(Thread::start);

        //执行join方法
        for (Thread thread : threads) {
            //join
            thread.join();
        }
        for (int i = 0; i < 10; i++) {
            executor(i);
        }
    }

    static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                executor(i);
            }
        }, String.valueOf(seq));
    }

    private static void executor(int i) {
        System.out.println(Thread.currentThread().getName() + "# " + i);
        shortSleep();
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
