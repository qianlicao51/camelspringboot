package com.study.thinkinginjava.char21.part5;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/20 20:37
 */
class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread().getName() + " ");
            }
        } catch (InterruptedException e) {

        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task implements Runnable {
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable {
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {

        final ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            exec.execute(new Task());
        }
        exec.execute(new Task2());
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;

            @Override
            public void run() {
                if (prod) {
                    System.out.println("notify()");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("notifyAll()");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.blocker.prodAll()");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow();

    }
}
