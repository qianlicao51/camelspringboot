package com.study.thread.c.char03;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/9 21:22
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DivTask implements Runnable {
    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.SECONDS
                , new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            //只有4个输出
            // pools.submit(new DivTask(100, i));

            //方式1
            pools.execute(new DivTask(100, i));

            //方式2
            // final Future<?> submit = pools.submit(new DivTask(100, i));
            // submit.get();
        }
    }

    @Override
    public void run() {
        double re = a / b;
        System.out.println(re);
    }
}
