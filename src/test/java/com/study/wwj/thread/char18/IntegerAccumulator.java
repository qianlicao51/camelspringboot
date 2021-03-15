package com.study.wwj.thread.char18;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 11:39
 */
//线程不安全的累加器
public class IntegerAccumulator {
    private int init;

    public IntegerAccumulator(int init) {
        this.init = init;
    }

    public static void main(String[] args) {
        //定义累加器
        final IntegerAccumulator accumulator = new IntegerAccumulator(0);
        //定义三个线程分别启动
        IntStream.range(0, 3).forEach(i -> {
            new Thread(() -> {
                int cur = 0;
                while (true) {
                    //首先获取old value
                    final int oldValue = accumulator.getValue();
                    //调动计算方法
                    final int result = accumulator.add(cur);
                    System.out.println(oldValue + " + " + cur + "=" + result);
                    if (cur + oldValue != result) {
                        System.out.println("Error");
                    }
                    cur++;
                    slowly();
                }
            }).start();
        });
    }

    private static void slowly() {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int add(int i) {
        this.init += i;
        return this.init;
    }

    public int getValue() {
        return init;
    }
}
