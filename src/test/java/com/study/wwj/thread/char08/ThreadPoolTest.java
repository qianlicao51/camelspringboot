package com.study.wwj.thread.char08;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 15:42
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        //初始化线程数 2，核心线程数4 最大线程数6 ，队列最多允许1000个任务
        final BasicThreadPool threadPool = new BasicThreadPool(2, 4, 6, 1000);

        //定义20个任务 并提交到任务
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(30);
                threadPool.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }, "").start();
        for (; ; ) {
            //不断输出 线程池的信息
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getQueueSize:" + threadPool.getQueueSize());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSize:" + threadPool.getMaxSize());
            System.out.println("======================");
            TimeUnit.SECONDS.sleep(5);
        }

    }
}
