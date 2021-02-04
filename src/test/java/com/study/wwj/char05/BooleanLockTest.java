package com.study.wwj.char05;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 17:30
 */
public class BooleanLockTest {
    public static void main(String[] args) {

        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1", "T2", "T3")
                .forEach(name ->
                        new Thread(() -> {
                            try {
                                booleanLock.lock();
                                work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                booleanLock.unlock();
                            }
                        }, name).start()
                );

    }


    private static void work() throws InterruptedException {
        Optional.of(Thread.currentThread().getName() + " is working")
                .ifPresent(System.out::println);
        TimeUnit.SECONDS.sleep(5);
    }
}

