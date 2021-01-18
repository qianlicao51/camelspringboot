package com.study.thinkinginjava.char21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 21:28
 */
public class ExceptionThread implements Runnable {
    public static void main(String[] args) {
        final ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ExceptionThread());
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
