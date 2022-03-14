package com.study.wwj.api.char02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author: MI
 * @Date: 2022/3/13/18:37
 * @Description:
 */
public class TryLockExample {
    private final static Object VAL_OBJ = new Object();

    public static void main(String[] args) {
        final TryLock lock = new TryLock();
        final List<Object> validation = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        if (lock.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + ":get the lock.");
                            if (validation.size() > 1) {
                                throw new IllegalStateException("validation failed.");
                            }
                            validation.add(VAL_OBJ);
                        } else {
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        }
                    } catch (InterruptedException e) {
                    } finally {
                        if (lock.release()) {
                            System.out.println(Thread.currentThread().getName() + ":release the lock");
                            validation.remove(VAL_OBJ);
                        }
                    }
                }
            }).start();
        }
    }
}
