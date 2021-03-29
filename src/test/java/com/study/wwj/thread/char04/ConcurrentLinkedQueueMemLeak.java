package com.study.wwj.thread.char04;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/26 10:36
 */
// 并发队列 内存泄漏问题
public class ConcurrentLinkedQueueMemLeak {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();

        //① 这一行会导致内存泄漏
        queue.add(new Object());

        Object ob = new Object();
        int loops = 0;

        //睡眠10秒，方便打开JDK诊断工具，监控执行前后的内存变化
        TimeUnit.SECONDS.sleep(10);

        Stopwatch watch = Stopwatch.createStarted();
        while (true) {
            // 每 10000 次执行一个字耗时统计，
            if (loops % 10_000 == 0 && loops != 0) {
                final long elapsed = watch.stop().elapsed(TimeUnit.MICROSECONDS);
                System.out.printf("loops=%d duration=%d MS%n", loops, elapsed);
                watch.reset().start();
            }
            queue.add(ob);
            //② 使用remove删除
            queue.remove(ob);
            ++loops;
        }
    }
}
