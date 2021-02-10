package com.study.wwj.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 11:01
 */

/**
 * 主要用于线程池内部，该类会使用到RunnableQueue ，
 * 然后不断地从queue中取出某个runnable，并运行runnable的方法
 */
public class InternalTask implements Runnable {
    private final RunnableQueue runnableQueue;
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()) {
            try {
                final Runnable take = runnableQueue.take();
                take.run();
            } catch (Exception e) {
                running = false;
                break;
            }
        }
    }

    public void stop() {
        this.running = false;
    }
}
