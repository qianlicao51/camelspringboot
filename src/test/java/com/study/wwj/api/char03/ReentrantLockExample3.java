package com.study.wwj.api.char03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 15:48
 */
public class ReentrantLockExample3 {
    public static void main(String[] args) {
        //启动10个线程
        final Accumulator accumulator = new Accumulator();
        for (int i = 0; i < 10; i++) {
            new AccumlatorThread(accumulator).start();
        }
    }

    private static class AccumlatorThread extends Thread {
        private final Accumulator accumulator;

        public AccumlatorThread(Accumulator accumulator) {
            this.accumulator = accumulator;
        }

        @Override
        public void run() {
            //不断地调用 addX addY
            while (true) {
                accumulator.addX();
                accumulator.addY();
                if (accumulator.getX() != accumulator.getY()) {
                    System.out.printf("The x:%d not equals y:%d\n", accumulator.getX(), accumulator.getY());
                }
            }
        }
    }

    private static class Accumulator {
        private static final Lock lock = new ReentrantLock();
        private int x = 0;
        private int y = 0;

        void addX() {
            lock.lock();
            try {
                x++;
            } finally {
                lock.unlock();
            }
        }

        void addY() {
            lock.lock();
            try {
                y++;
            } finally {
                lock.unlock();
            }
        }

        int getX() {
            lock.lock();
            try {
                return x;
            } finally {
                lock.unlock();
            }
        }

        int getY() {
            lock.lock();
            try {
                return y;
            } finally {
                lock.unlock();
            }
        }
    }
}
