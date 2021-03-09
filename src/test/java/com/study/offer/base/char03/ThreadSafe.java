package com.study.offer.base.char03;

import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/5 21:45
 */
public class ThreadSafe extends Thread {
    public static void main(String[] args) throws InterruptedException {
        final ThreadSafe safe = new ThreadSafe();
        safe.start();
        TimeUnit.MILLISECONDS.sleep(500);
        safe.interrupt();
    }

    @Override
    public void run() {
        //在非阻塞过程中判断中断标志来退出
        while (!isInterrupted()) {
            try {
                //在阻塞过程中捕获中断异常来退出
                TimeUnit.SECONDS.sleep(1);
                System.out.println(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            } catch (InterruptedException e) {
                e.printStackTrace();
                //捕获到异常后执行break跳出循环
                break;
            }
        }
    }
}
