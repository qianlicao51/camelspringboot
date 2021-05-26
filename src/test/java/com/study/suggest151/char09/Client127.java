package com.study.suggest151.char09;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/26 10:08
 */
public class Client127 {
    public static void main(String[] args) throws Exception {
        runTasks(TaskWithLock.class);
        runTasks(TaskWithSync.class);
    }

    public static void runTasks(Class<? extends Runnable> clz) throws Exception {
        final ExecutorService es = Executors.newCachedThreadPool();
        System.out.println("***开始执行***" + clz.getSimpleName() + "任务***");
        //启动三个线程
        for (int i = 0; i < 3; i++) {
            es.submit(clz.newInstance());
        }
        //等待足够长的时间，关闭执行器
        TimeUnit.SECONDS.sleep(10);
        System.out.println("******" + clz.getSimpleName() + "任务执行完毕***\n");
    }

}

class Task {
    public void doSomething() {
        try {
            TimeUnit.SECONDS.sleep(2l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        sb.append("线程名：" + Thread.currentThread().getName());
        sb.append("，执行时间:" + Calendar.getInstance().get(13) + " s");
        System.out.println(sb);
    }
}

//显示锁
class TaskWithLock extends Task implements Runnable {
    private static final Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        try {
            doSomething();
        } finally {
            lock.unlock();
        }
    }
}

class TaskWithSync extends Task implements Runnable {
    @Override
    public void run() {
        synchronized ("A") {
            doSomething();
        }
    }
}
