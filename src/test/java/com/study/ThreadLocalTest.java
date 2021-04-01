package com.study;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/29 16:03
 */
@Slf4j
public class ThreadLocalTest {
    ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "hello");
    ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    HashMap<String, String> hashMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final int processors = Runtime.getRuntime().availableProcessors();
        log.info("processors={}", processors);
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, processors, 3, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3));

        for (int i = 0; i < 3; i++) {
            poolExecutor.execute(() -> {
                sleep();
            });
        }


        /*while (true) {
            log.info(poolExecutor.getQueue().size() + "|" + poolExecutor.getPoolSize());
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
            }
        }*/
        log.info("all task submit finished");
        poolExecutor.shutdown();
        //一般情况下会和shutdown方法组合使用|此处是为了阻塞，等到线程执行完毕在执行下面代码
        if (!poolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("Some tasks were not terminated");
        }
        log.info("poolExecutor is shutdown");
        poolExecutor.shutdown();
    }

    static void sleep() {
        try {
            log.info(Thread.currentThread().getName() + " start");
            final int nextInt = ThreadLocalRandom.current().nextInt(2);
            TimeUnit.SECONDS.sleep(nextInt);
            log.info(Thread.currentThread().getName() + "<>en" + nextInt);
        } catch (InterruptedException e) {
        }

    }
}
