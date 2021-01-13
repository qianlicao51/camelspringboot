package com.study.onjava8.char06;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/13 10:56
 */
// housekeeping/MethodInit3.java

public class MethodInit3 {
    // int j = g(i); // Illegal forward reference
    int i = f();

    int f() {
        return 11;
    }

    int g(int n) {
        return n * 10;
    }
}
