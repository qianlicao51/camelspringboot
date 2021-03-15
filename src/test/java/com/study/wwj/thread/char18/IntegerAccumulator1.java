package com.study.wwj.thread.char18;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 12:31
 */
//不可变对象不允许被继承
public final class IntegerAccumulator1 {
    private final int init;

    public IntegerAccumulator1(int init) {
        this.init = init;
    }

    //构造累加器，需要用到另外一个accumulator和初始值
    public IntegerAccumulator1(IntegerAccumulator1 accumulator, int init) {
        this.init = accumulator.getValue() + init;
    }

    public static void main(String[] args) {
        //定义累加器
        final IntegerAccumulator1 accumulator = new IntegerAccumulator1(0);
        //定义三个线程分别启动
        IntStream.range(0, 3).forEach(i -> {
            new Thread(() -> {
                int cur = 0;
                while (true) {
                    //首先获取old value
                    final int oldValue = accumulator.getValue();
                    //调动计算方法
                    final int result = accumulator.add(cur).getValue();
                    System.out.println(oldValue + " + " + cur + "=" + result);
                    if (cur + oldValue != result) {
                        System.out.println("Error===========================");
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

    public int getValue() {
        return init;
    }

    //每一次相加都会产生一个新的
    public IntegerAccumulator1 add(int i) {
        return new IntegerAccumulator1(this, i);
    }
}
