package com.study.wwj.char05;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 17:30
 */
public class BooleanLockTest {
    private final static Random random = new Random(System.currentTimeMillis());

    private final Lock lock = new BooleanLock();

    public static void main(String[] args) {
        final BooleanLockTest blt = new BooleanLockTest();
        extracted(blt);
    }

    private static void extracted(BooleanLockTest blt) {
        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(() -> {
                    blt.syncMethod();
                }))
                .forEach(Thread::start);
    }

    private void syncMethod() {
        try {
            lock.lock(1000);

            final int nextInt = random.nextInt(10);
            System.out.println(Thread.currentThread().getName() + " get the lock. ");
            TimeUnit.SECONDS.sleep(nextInt);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is working")
                .ifPresent(System.out::println);
        TimeUnit.SECONDS.sleep(5);
    }
}

