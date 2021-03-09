package com.study.offer.base.char03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 22:25
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int N = 4;
        // 定义 Cycicbarrier
        final CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new BusinessThread(barrier).start();
        }
    }

    static class BusinessThread extends Thread {
        private CyclicBarrier cycicbarrier;

        public BusinessThread(CyclicBarrier cycicbarrier) {
            this.cycicbarrier = cycicbarrier;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("线程执行前准备工作完成，等待其他线程准备工作完成");
                cycicbarrier.await();
                System.out.println("所有线程准备工作均完成，执行下一项任务");
            } catch (InterruptedException | BrokenBarrierException e) {
            }
        }
    }
}
