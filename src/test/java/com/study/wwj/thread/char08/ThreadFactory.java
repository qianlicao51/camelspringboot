package com.study.wwj.thread.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 10:35
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
