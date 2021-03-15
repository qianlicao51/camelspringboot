package com.study.wwj.thread.char20;

import java.util.LinkedList;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 15:13
 */
public class GuardedSuspensionQueue {
    //定义存放 Integer 类型的 Queue
    private final LinkedList<Integer> queue = new LinkedList<>();
    //定义最大容量为 100
    private final int LIMIT = 100;

    //往queue中插入数据，如果 元素超过了最大容量，则会陷入阻塞
    public void offer(Integer data) throws InterruptedException {
        synchronized (this) {
            //判断queue 的当前元素是否超过了LIMIT
            while (queue.size() >= LIMIT) {
                //挂起当前线程，使其陷入阻塞
                this.wait();
            }
            //插入元素,并唤醒take线程
            queue.addLast(data);
            this.notifyAll();
        }
    }

    //从队列中获取元素，如果队列为空，则会使当前线程阻塞
    public Integer take() throws InterruptedException {
        synchronized (this) {
            if (queue.isEmpty()) {
                this.wait();
            }
            //通知offer可以继续插入数据
            this.notifyAll();
            return queue.removeFirst();
        }
    }
}
