package com.study.wwj.api.char04;

import com.google.common.base.Stopwatch;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 21:53
 */
public class ConcurrentLinkedQueueMemLeak {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Object> queue = new ConcurrentLinkedQueue<>();
        //① 这一行代码会导致内存泄漏
        // queue.add(new Object());
        Object object = new Object();

        int loop = 0;
        //睡眠10秒，方便打开JDK诊断工具，监控执行前后内存变化
        TimeUnit.SECONDS.sleep(10);

        final Stopwatch wathc = Stopwatch.createStarted();
        while (true) {
            //每10000次进行一次统计耗时统计，并输出
            if (loop % 10000 == 0 && loop != 0) {
                final long elapsedMs = wathc.stop().elapsed(TimeUnit.MILLISECONDS);
                System.out.printf("loops=%d duration=%dMS\n", loop, elapsedMs);
                wathc.reset().start();
            }
            queue.add(object);
            //② remove 方法删除
            queue.remove(object);
            ++loop;
        }
    }
}
