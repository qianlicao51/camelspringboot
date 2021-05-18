package com.study.jvm.char04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/18 16:07
 */
public class JStat {






















    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        new Thread(() -> {
            while (true) {
                ;
            }
        }, "testBusyThread").start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        final Thread thread = new Thread(() -> {

            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        createBusyThread();
        reader.readLine();
        final Object obj = new Object();
        createLockThread(obj);
    }
}
