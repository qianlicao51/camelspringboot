package com.study;

import com.study.utils.SysUtils;

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
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(SysUtils.getDate());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
