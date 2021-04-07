package com.study;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/1 17:09
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 100, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000));
    }
}
