package com.study.book.gym.Char03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/13 10:52
 */
public class ReenterLockCondition implements Runnable {
    public static final ReentrantLock lock = new ReentrantLock();
    public static final Condition condition = lock.newCondition();

    @Override
    public void run() {

        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final ReenterLockCondition tl = new ReenterLockCondition();
        final Thread t1 = new Thread(tl);
        t1.start();
        TimeUnit.SECONDS.sleep(2);

        /**
         *使用Condition.await方法时，要求线程持有相关重入锁。在调用Condition.await方法时，会释放这把锁
         * 在调用condition.signal() ，也要求线程先获得相关的锁，在调用signal后，也要释放相关的锁
         *
         */
        lock.lock();
        //IllegalMonitorStateException
        condition.signal();
        lock.unlock();
    }
}
