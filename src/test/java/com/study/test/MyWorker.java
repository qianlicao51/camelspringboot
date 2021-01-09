package com.study.test;

import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 22:36
 * 编写一个 线程类，需要继承Thread，设计一个线程名称。
 * 设计一个集合用于保存所有任务
 */
public class MyWorker extends Thread {

    private List<Runnable> tasks;

    public MyWorker(String name, List<Runnable> tasks) {
        super(name);
        this.tasks = tasks;
    }

    @Override
    public void run() {
        //判断集合中是否有任务，只要有就一直执行
        while (tasks.size() > 0) {
            final Runnable r = tasks.remove(0);
            r.run();
        }
    }
}
