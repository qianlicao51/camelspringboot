package com.study.wwj.api.char03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 16:02
 */
public class CyclicBarrierExample2 {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        //定义CyclicBarrier 这里的 parties 值为11
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(11);
        //创建10 个线程
        for (int i = 0; i < 10; i++) {
            //定义旅客线程，传入旅客编号和barrier
            new Thread(new Tourist(i, cyclicBarrier)).start();
        }
        //主线程池等待所有游客都上了大巴车
        cyclicBarrier.await();
        System.out.println("Tour guider :all of tourist get on the bus");

        //主线程进入阻塞，等待所有旅客下大巴车
        cyclicBarrier.await();
        System.out.println("Tour guider :all of tourist get off the bus");
    }

    private static class Tourist implements Runnable {
        private final int touristID;
        private final CyclicBarrier barrier;

        public Tourist(int touristID, CyclicBarrier barrier) {
            this.touristID = touristID;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.printf("Tourist:%d by bus\n", touristID);
            //模拟乘客上车的时间开销
            this.spendSeveralSeconds();
            //上车后等待其他同伴上车
            this.waitAndPrint("Tourist:%d get on the bus,and await other people reached. \n");
            //模拟乘客下车的时间开销
            this.spendSeveralSeconds();
            //下车后等待其他同伴下车
            this.waitAndPrint("Tourist:%d get off the bus,and await other people get off \n");

        }

        private void waitAndPrint(String msg) {
            System.out.printf(msg, touristID);
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
            }
        }

        private void spendSeveralSeconds() {
            try {
                TimeUnit.SECONDS.sleep(current().nextInt(10));
            } catch (InterruptedException e) {
            }
        }
    }
}
