package com.study.thread.wwj.char02;

import java.util.stream.IntStream;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/3 16:57
 */
public class Demo01 {
    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().map(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        })).forEach(Thread::start);
    }
}
