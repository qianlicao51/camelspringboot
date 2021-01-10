package com.study.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/10 14:20
 */
public class MyTest01 {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            service.submit(new MyRunnable(i));
        }
        /*service.execute(
            () -> {
            // ... do something inside runnable task

        });*/
        service.shutdown();

    }
}

class MyRunnable implements Runnable {
    private int id;

    public MyRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();
        System.out.println(name + "执行了任务" + id);
    }
}
