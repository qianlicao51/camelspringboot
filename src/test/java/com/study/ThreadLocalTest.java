package com.study;

import com.study.utils.SysUtils;
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

    public static void main(String[] args) {
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1));

        poolExecutor.execute(() -> {
            sleep();
            System.out.println(Thread.currentThread().getName());
        });
        poolExecutor.execute(() -> {
            sleep();
            log.info(SysUtils.getDateYmd());
        });
        poolExecutor.execute(() -> {
            sleep();
            log.info(SysUtils.getDateYmd());
        });

        while (true) {
            log.info(poolExecutor.getQueue().size() + "|" + poolExecutor.getPoolSize());
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
