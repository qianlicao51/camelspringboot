package com.study.wwj.thread.char15;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 14:27
 */
public interface Observable {
    //获取当任务的生命周期状态
    Cycle getCycle();

    //定义启动线程的方法，主要作用是为了屏蔽Thread的其他方法
    void start();

    //定义线程的打断方法，作用于start方法一样，也是为了屏蔽 Thread 的其他方法
    void interrupt();

    //任务 生命周期的枚举类型
    enum Cycle {
        STARTED, RUNNING, DONE, ERROR
    }
}
