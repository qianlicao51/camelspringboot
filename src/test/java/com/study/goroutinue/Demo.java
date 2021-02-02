package com.study.goroutinue;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/2 16:50
 */
public class Demo {
    public static void main(String[] args) {
        for (int i = 0; i < 1_000_000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println("Demo.main");
    }
}
