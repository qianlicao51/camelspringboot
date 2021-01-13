package com.study.onjava8.char06;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/13 10:55
 */
public class MethodInit2 {

    int i = f();
    int j = g(i);

    int f() {
        return 11;
    }

    int g(int n) {
        return n * 10;
    }

    public static void main(String[] args) {

    }
}
