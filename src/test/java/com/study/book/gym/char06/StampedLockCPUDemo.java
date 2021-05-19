package com.study.book.gym.char06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/19 10:45
 */
public class StampedLockCPUDemo {
    static Thread[] holdCpuThreads = new Thread[3];
    static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            final long readLong = lock.writeLock();
            LockSupport.parkNanos(600000000000L);
            lock.unlockWrite(readLong);
        }).start();
        TimeUnit.MILLISECONDS.sleep(100);
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i] = new Thread(new HoldCPUReadThread());
            holdCpuThreads[i].start();

        }
        TimeUnit.SECONDS.sleep(10);
        //线程中断后 ，会占用CPU
        for (int i = 0; i < 3; i++) {
            holdCpuThreads[i].interrupt();
        }
    }

    private static class HoldCPUReadThread implements Runnable {
        @Override
        public void run() {
            final long lockr = StampedLockCPUDemo.lock.readLock();
            System.out.println(Thread.currentThread().getName() + " 获得锁");
            lock.unlockRead(lockr);
        }
    }
}
