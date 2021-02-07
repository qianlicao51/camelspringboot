package com.study.wwj.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/7 16:45
 */
public interface ThreadPool {
    /**
     * 替吉奥你任务到线程池
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取线程池初始化大小
     *
     * @return 线程池初始化大小
     */
    int getInitSize();

    /**
     * 获取线程池最大的线程数
     */
    int getMaxSize();

    /**
     * 获取线程池中用于缓存任务队列的大小
     *
     * @return
     */
    int getQueueSize();

    /**
     * 获取线程池中活跃线程的数量
     *
     * @return
     */
    int getActiveCount();

    /**
     * 查看线程池是否已经被shutdown
     *
     * @return
     */
    boolean isShutdown();
}
