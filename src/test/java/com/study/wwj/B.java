package com.study.wwj;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 16:01
 */
public class B {

    public static final Lock lock = new ReentrantLock();
    private final String name;
    private final A a;

    public B(String name) {
        this.name = name;
        this.a = new A(this);
        toString();
    }

    public static void main(String[] args) {
        final B b = new B("b");
        System.out.println(b);
    }

    public String getName() {
        return name;
    }

    public A getA() {
        return a;
    }

    @Override
    public String toString() {
        final String s = "B{" +
                "1name='" + name + '\'' +
                '}';
        System.out.println(s);
        return s;
    }
}
