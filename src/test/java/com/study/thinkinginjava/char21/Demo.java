package com.study.thinkinginjava.char21;

import java.util.concurrent.*;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/13 23:23
 */
public class Demo {
    public static void main(String[] args) {
        ConcurrentMap concurrentMap = new ConcurrentHashMap<String, String>();
        ExecutorService service = Executors.newCachedThreadPool();
        // ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 200; i++) {
            service.execute(
                    () -> {
                        // ... do something inside runnable task
                        final String name = Thread.currentThread().getName();
                        // System.out.println(name);
                        concurrentMap.put(name, name);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
        }
        //调用该方法可以防止新任务被提交给 这个Executor
        System.out.println("submit finished");
        service.shutdown();
        System.out.println(concurrentMap.size());
    }

}
