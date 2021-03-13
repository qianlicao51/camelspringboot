package com.study.wwj.api.char03;

import java.util.concurrent.locks.StampedLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 21:07
 */
public class StampedLockExample3 {
    //定义锁
    private static final StampedLock lock = new StampedLock();
    //共享数据
    private static int shareData = 0;

    public static void inc() {
        final long stamp = lock.writeLock();
        try {
            //修改共享数据
            shareData++;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static int get() {
        //注释 ①
        long stamp = lock.tryOptimisticRead();
        //注释 ②
        if (!lock.validate(stamp)) {
            // 注释 ③
            stamp = lock.readLock();
            try {
                return shareData;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return shareData;
    }
}
