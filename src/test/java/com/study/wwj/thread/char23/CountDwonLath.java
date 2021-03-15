package com.study.wwj.thread.char23;

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
