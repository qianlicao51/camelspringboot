package com.study.offer.base.char03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 16:00
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // step 1 设置线程数
        int printNumber = 5;
        // step 2 设置并发数
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < printNumber; i++) {
            new Worker(i, semaphore).start();
        }
    }

    public void demo() {
        // step 1 创建一个计数阀值为5的信号量对象，即只能有5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        try {
            //step 2 申请许可
            semp.acquire();
            try {
                // step 3 执行业务逻辑
            } catch (Exception e) {
            } finally {
                //step 4 释放许可
                semp.release();
            }
        } catch (InterruptedException e) {
        }
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //step 3 线程申请资源，
                semaphore.acquire();
                System.out.println("员工" + this.num + " 占用一个打印机");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("员工" + this.num + " 打印完成，释放出打印机");
            } catch (InterruptedException e) {
            } finally {
                // step 4 线程释放资源
                semaphore.release();
            }
        }
    }
}
