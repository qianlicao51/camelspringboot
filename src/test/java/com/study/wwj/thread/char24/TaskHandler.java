package com.study.wwj.thread.char24;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 16:37
 */

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 处理用户提交的每一个Request 请求，
 */
public class TaskHandler implements Runnable {
    //需要处理的请求
    private final Request request;

    public TaskHandler(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        System.out.println("Begin handle " + request);
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End handle " + request);
    }
}
