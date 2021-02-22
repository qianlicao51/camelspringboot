package com.study.wwj;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/10 16:01
 */
public class A {
    private final B b;

    public A(B b) {
        synchronized (B.class) {
            this.b = b;
            toString();
        }
    }

    @Override
    public String toString() {
        final String s = "A{" +
                "b=" + b.getName() +
                '}';
        System.out.println(s);
        return s;
    }
}

