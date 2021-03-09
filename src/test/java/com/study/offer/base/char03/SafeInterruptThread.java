package com.study.offer.base.char03;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/5 21:14
 */
public class SafeInterruptThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        //3 定义一个可安全退出的线程
        final SafeInterruptThread thread = new SafeInterruptThread();
        thread.start();
        TimeUnit.MILLISECONDS.sleep(500);
        thread.interrupt();
    }

    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                //1 这里处理正常的线程业务逻辑
                sleep(1000);
            } catch (InterruptedException e) {
                //重新设置中断标识
                System.out.println("重新设置中断标识");
                Thread.currentThread().interrupt();
            }
        }
        if (Thread.currentThread().isInterrupted()) {
            //2 处理线程结束前必要的一些资源释放和清理工作，比如释放锁，存储数据到持久化、发出异常通知，用于实现线程安全退出
            // sleep(10);
        }
    }
}
