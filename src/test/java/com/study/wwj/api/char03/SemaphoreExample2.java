package com.study.wwj.api.char03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/9 17:19
 */
public class SemaphoreExample2 {
    public static void main(String[] args) {
        final TryLock tryLock = new TryLock();
        //启动一个线程，尝试获取tryLock 如果获取不成功 则将进行其他的操作，该线程不进入阻塞
        new Thread(() -> {
            final boolean gotlock = tryLock.tryLock();
            if (!gotlock) {
                System.out.println(Thread.currentThread() + " can't get the lock ,will do other thing");
                return;
            }
            try {
                simulateWork();
            } finally {
                tryLock.unlock();
            }
        }).start();
        //主线程也参与tryLock 的争抢，如果抢不到，则main 线程不会进去阻塞
        final boolean b = tryLock.tryLock();
        if (!b) {
            System.out.println(Thread.currentThread() + " can't get the lock ,will do other thing");
        } else {
            try {
                simulateWork();
            } finally {
                tryLock.unlock();
            }
        }
    }

    private static void simulateWork() {
        try {
            System.out.println(Thread.currentThread() + " get lock and do working ");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
        }
    }

    private static class TryLock {
        //定义 permit 为1的Semaphore
        private final Semaphore semaphore = new Semaphore(1);

        public boolean tryLock() {
            return semaphore.tryAcquire();
        }

        public void unlock() {
            semaphore.release();
            System.out.println(Thread.currentThread() + " release lock ");
        }
    }
}
