package com.study.thinkinginjava.char21.part4;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/18 22:34
 */


/**
 * 表示可中断的阻塞
 */
class SleepBlocked implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

/**
 * 表示不可中断的阻塞
 */
class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read():");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("isInterrupted from Blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocking.run()");
    }
}

/**
 * 表示不可中断的阻塞
 */
class SynchronizedBlocked implements Runnable {
    public SynchronizedBlocked() {
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    public synchronized void f() {
        while (true) {
            Thread.yield();
        }
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchrionizedBlocked.run()");
    }
}

public class Interrupting {
    final static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        final Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        test(new SleepBlocked());
        System.out.println();
        test(new IOBlocked(System.in));
        System.out.println();
        test(new SynchronizedBlocked());
        System.out.println();
        System.out.println("Aborting with System.exxit(0)");
        System.exit(0);
    }
}
