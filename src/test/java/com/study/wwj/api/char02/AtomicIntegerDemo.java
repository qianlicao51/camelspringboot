package com.study.wwj.api.char02;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname AtomicIntegerDemo
 * @Version 1.0.0
 * @Date 2022/3/11 18:18
 * @Created by yd
 */
public class AtomicIntegerDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);
        System.out.println(atomicInteger);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
    }
}
