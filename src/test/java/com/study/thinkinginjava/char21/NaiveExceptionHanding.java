package com.study.thinkinginjava.char21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 21:32
 */
public class NaiveExceptionHanding {
    public static void main(String[] args) {
        try {
            final ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());
        } catch (RuntimeException e) {
            System.out.println("exception has been handled");
        }
    }
}
