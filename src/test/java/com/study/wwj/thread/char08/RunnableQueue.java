package com.study.wwj.thread.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/7 17:07
 */

/**
 * 任务队列，主要用于缓存提交到线程池中的任务
 */
public interface RunnableQueue {
    /**
     * 当有新的任务进来是首先会offer到队列中
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取runnable
     * @return
     */
    Runnable take();

    /**
     * 获取任务队列中的数量
     * @return
     */
    int size();
}
