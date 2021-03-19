package com.study.wwj.thread.char23;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 17:10
 */
//无限等待CountDowLatch
public class CountDwonLath extends Lathch {
    public CountDwonLath(int limit) {
        super(limit);
    }

    @Override
    public void await() throws InterruptedException {
        synchronized (this) {
            //当limit 大于0 ，当前线程进入阻塞状态
            while (limit > 0) {
                this.wait();
            }
        }
    }

    /**
     * 超时机制
     * 为了方便计算，我们将所有时间都换算成了纳秒，但是wait方法只能接受毫秒，因此该方法还涉及了时间的换算。
     * 如果的等待时间不足1毫秒，那么 会抛出超时异常。
     *
     * @param unit
     * @param time
     * @throws InterruptedException
     */
    @Override
    public void await(TimeUnit unit, long time) throws InterruptedException, WaitTimeoutException {
        if (time <= 0) {
            throw new IllegalStateException("the time is invalid ");
        }
        long reaminingNanos = unit.toNanos(time);
        //等待任务将在 endNanos纳秒后超时
        final long endNamos = System.nanoTime() + reaminingNanos;

        synchronized (this) {
            while (limit > 0) {
                //如果超时则抛出Wait
                if (TimeUnit.NANOSECONDS.toMillis(reaminingNanos) <= 0) {
                    throw new WaitTimeoutException("the wait time over specify time");
                }
                //等待 remainingNanos,在等待的过程中可能被中断，重新计算 remainingNanos
                this.wait(TimeUnit.NANOSECONDS.toMillis(reaminingNanos));
                reaminingNanos = endNamos - System.nanoTime();
            }
        }
    }

    @Override
    public void countDown() {
        synchronized (this) {
            if (limit <= 0) {
                throw new IllegalStateException("all of task already arrived");
            }
            //limit 减一,并通知阻塞线程
            limit--;
            this.notifyAll();
        }
    }

    @Override
    public int getUnarrived() {
        //返回有多少任务还未完成任务
        return limit;
    }
}
