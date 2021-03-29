package com.study;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/23 21:01
 */
public class Interrupted {
    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            new Thread(() -> {
                while (true) {

                }
            }, "").start();

        }
    }
}
