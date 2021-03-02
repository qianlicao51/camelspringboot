package com.study.bookcurrent.char03;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/1 11:01
 */
public class NoBisibility {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            System.out.println(ready);
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}
