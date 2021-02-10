package com.study.wwj.char08;

import java.util.LinkedList;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 11:07
 */
public class LinkedRunnableQueue implements RunnableQueue {
    /**
     * 任务队列的最大容量，
     */
    private final int limit;
    /**
     * 拒绝策略
     */
    private final DenyPolicy denyPolicy;

    /**
     * 存放任务的队列
     */
    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * 如果队列数量达到了上限，就会执行拒绝策略，否则将任务存放到队列中，同时唤醒take任务的线程。
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList) {
            if (runnableLinkedList.size() >= limit) {
                //无法容纳新的任务是执行拒绝策略
                denyPolicy.reject(runnable, threadPool);
            } else {
                //将任务放入队尾，并且唤醒阻塞中的队列
                runnableLinkedList.addLast(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() {
        synchronized (runnableLinkedList) {
            while (runnableLinkedList.isEmpty()) {
                try {
                    runnableLinkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList) {
            return runnableLinkedList.size();
        }
    }
}
