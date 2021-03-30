package com.study.blog.why.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/30 9:54
 */
// stampedLock CPU100%例子
//http://ifeve.com/stampedlock-bug-cpu/
@Slf4j
public class TestStampedLock {
    public static void main(String[] args) throws InterruptedException {
        final StampedLock lock = new StampedLock();
        new Thread(() -> {
            long readLong = lock.writeLock();
            log.info("parkNanos");
            LockSupport.parkNanos(6100000000L);
            lock.unlockWrite(readLong);
            log.info("unlockWrite");
        }).start();
        Thread.sleep(100);
        for (int i = 0; i < 3; ++i)
            new Thread(new OccupiedCPUReadThread(lock)).start();
    }

    private static class OccupiedCPUReadThread implements Runnable {
        private final StampedLock lock;

        public OccupiedCPUReadThread(StampedLock lock) {
            this.lock = lock;
        }

        public void run() {
            Thread.currentThread().interrupt();
            long lockr = lock.readLock();
            log.info(Thread.currentThread().getName() + " get read lock");
            lock.unlockRead(lockr);
        }
    }
}
