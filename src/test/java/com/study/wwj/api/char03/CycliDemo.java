package com.study.wwj.api.char03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/9 9:06
 */
public class CycliDemo {
    public static void main(String[] args) throws InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(2);
        final Thread thread = new Thread(() -> {
            try {
                //thread 会进入阻塞状态
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        //两秒之后执行 thread的中断方法
        TimeUnit.SECONDS.sleep(2);
        //调用中断
        thread.interrupt();
        // 短暂睡眠，确保thread 的执行动作发生在main线程读取broken状态之前
        TimeUnit.SECONDS.sleep(2);
        System.out.println(barrier.isBroken());
    }
}
