package com.study.wwj.thread.char23;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 17:05
 */
public abstract class Lathch {
    //用于控制多少个线程完成任务时才能打开阀门
    protected int limit;

    public Lathch(int limit) {
        this.limit = limit;
    }

    //该方法会使得当前线程一直等待，直到所有的线程都完成工作，被阻塞的线程是运行被中断的。
    public abstract void await() throws InterruptedException;

    public abstract void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException;

    //当前任务完成工作之后调用该方法使得计数器减一
    public abstract void countDown();

    //获取当前还有多少个线程没有完成任务
    public abstract int getUnarrived();
}
