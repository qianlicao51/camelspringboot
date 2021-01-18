package com.study.thinkinginjava.char21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 22:02
 */
class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        final Thread thread = Thread.currentThread();
        System.out.println("run() by" + thread);
        System.out.println("eh = " + thread.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        final Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh= " + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {

        final ExecutorService service = Executors.newCachedThreadPool(new HandlerThreadFactory());
        service.execute(new ExceptionThread2());

        service.shutdown();
    }
}
