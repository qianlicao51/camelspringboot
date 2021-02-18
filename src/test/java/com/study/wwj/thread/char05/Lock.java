package com.study.wwj.thread.char05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 17:02
 */
public interface Lock {
    /**
     * 永远阻塞，除非获得了锁
     *
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;

    /**
     * 超时功能的锁
     *
     * @param mills 超时时间
     * @throws InterruptedException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 用来进行锁的释放
     */
    void unlock();

    /**
     * 获取当前哪些线程被阻塞
     *
     * @return
     */
    List<Thread> getBlockedThreads();

    int getBlockedSize();
}
