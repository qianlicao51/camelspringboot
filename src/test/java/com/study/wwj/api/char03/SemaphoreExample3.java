package com.study.wwj.api.char03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/11 12:54
 */
public class SemaphoreExample3 {
    public static void main(String[] args) throws InterruptedException {
        //定义只有一个许可证的semaphore
        final Semaphore semaphore = new Semaphore(1, true);
        final Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("The thread t1 acquired permit form semaphore");
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("the thread t1 is Interrupted");
            } finally {
                semaphore.release();
            }
        });
        t1.start();
        //为了确保t1 启动 ，在主线程中 休眠 1 秒 等待
        TimeUnit.SECONDS.sleep(1);
        final Thread t2 = new Thread(() -> {
            try {
                //阻塞式获取一个许可证
                semaphore.acquire();
            } catch (InterruptedException e) {
                System.out.println("the thread t2 is Interrupted");
                //出现异常则不再进行
                return;
            }
            try {
                System.out.println("The thread t2 acquired permit form semaphore");
            } catch (Exception e) {
            } finally {
                semaphore.release();
            }
        });
        //启动 t2,休眠2s
        t2.start();
        TimeUnit.SECONDS.sleep(2);
        //对线程t2 中断
        t2.interrupt();
        //主线程获取许可证
        semaphore.acquire();
        System.out.println("the main thread acquired permit.");
    }
}
