package com.study.test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 22:41
 * 成员变量，需要包含任务队列
 * 1 任务队列，集合 需要控制线程安全
 * 2 当前线程数量
 * 3 核心线程数
 * 4 最大线程数
 * 5 任务队列的长度
 * <p>
 * 成员方法：
 * 1 提交任务，将任务添加到集合。
 * 2 执行任务，判断当前线程的数量，决定创建核心线程数量、
 */
public class MyThreadPool {
    //1 任务队列
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());
    //    2 当前线程数量
    private int num = 0;

    //  3 核心线程数量
    private int corePoolSize;

    //    4 最大线程数量
    private int maxSize;

    //    5 任务队列的长度
    private int workSize;

    public MyThreadPool(int corePoolSize, int maxSize, int workSize) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    //    1 提交任务
    public void submit(Runnable r) {
        //判断当前集合的中的数量，是否超出了最大任务数量
        if (tasks.size() >= workSize) {
            System.out.println("任务" + r + "被丢弃了");
        } else {
            tasks.add(r);
            //执行任务
            execTask(r);
        }
    }

    //执行任务
    private void execTask(Runnable r) {
        //   判断当前线程池中的总数量，是否超出了核心数
        if (num < corePoolSize) {
            new MyWorker("核心线程" + num, tasks).start();
            num++;
        } else if (num < maxSize) {
            new MyWorker("非核心线程" + num, tasks).start();
            num++;
        } else {
            System.out.println("任务" + r + "被缓存了……");
        }
    }
}
