package com.study.wwj.api.char02;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Classname TryLock
 * @Version 1.0.0
 * @Date 2022/3/11 18:32
 * @Created by yd
 * Try
 */
public class TryLock {
    // 借助于 AtomicBoolean 的布尔值原子性操作方法
    private final AtomicBoolean ab = new AtomicBoolean(false);

    // 线程保险箱，用于存放与线程上下文关联的数据副本
    private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

    /**
     * 可立即返回的lock方法
     *
     * @return
     */
    public boolean tryLock() {
        // 借助于 AtomicBoolean 的CAS 操作对布尔值进行修改
        boolean result = ab.compareAndSet(false, true);
        if (result) {
            //修改成功 ，同步更新 threadLocal 的数据副本
            threadLocal.set(true);
        }
        return result;
    }

    //锁的释放
    public boolean release() {
        // 判断调用release的线程是否成功获取了锁
        if (threadLocal.get()) {
            threadLocal.set(false);
            // 标记锁释放，并且原子性地修改布尔值为false
            return ab.compareAndSet(true, false);
        } else {
            return false;
        }
    }

}
