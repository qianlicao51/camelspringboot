package com.study.thinkinginjava.char21.part3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 22:16
 */
public class EventChecker implements Runnable {
    private final int id;
    private IntGenerator generator;

    public EventChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    public static void test(IntGenerator gp, int count) {
        System.out.println("press control-c exit");
        final ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EventChecker(gp, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 10);
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println(val + " not event");
                generator.cancel();
            }
        }
    }
}
