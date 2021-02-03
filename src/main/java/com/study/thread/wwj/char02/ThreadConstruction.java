package com.study.thread.wwj.char02;

import java.util.stream.IntStream;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/3 17:06
 */
public class ThreadConstruction {
    private final static String PREFIX = "ALEX-";

    public static void main(String[] args) {
        IntStream.range(0, 5).mapToObj(ThreadConstruction::createdThread)
                .forEach(Thread::start);
    }

    private static Thread createdThread(final int intName) {
        return new Thread(() ->
                System.out.println(Thread.currentThread().getName())
                , PREFIX + intName);
    }
}
