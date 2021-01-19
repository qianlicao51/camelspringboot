package com.study.thinkinginjava.char21.part4;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/19 22:13
 */

class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int id) {
        this.id = id;
        System.out.println("NeedsCleanup " + id);
    }

    public void cleanup() {
        System.out.println("Cleaning up " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                final NeedsCleanup cleanup = new NeedsCleanup(1);
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    NeedsCleanup c2 = new NeedsCleanup(2);
                    try {
                        System.out.println("Calculating");
                        for (int i = 0; i < 250000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        System.out.println("finished time-consuming operation");
                    } finally {
                        c2.cleanup();
                    }
                } finally {
                    cleanup.cleanup();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("exiting via InterruptedException ");
        }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        final Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        t.interrupt();
    }
}
