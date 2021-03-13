package com.study.wwj.api.char03;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 16:16
 */
public class ConditionExample {
    //创建显示锁
    private static final Lock lock = new ReentrantLock();
    //① 使用显示锁创建Condition 对象并且与之关联
    private static final Condition condition = lock.newCondition();
    //定义共享数据
    private static int shareData = 0;
    //定义布尔变量标识当前的共享数据是否已经被使用
    private static boolean dataUsed = false;

    //对数据的写操作
    private static void change() {
        //获取锁，如果当前锁被其他线程持有，则当前线程会进入阻塞
        lock.lock();
        try {
            //② 如果当前锁被其他线程持有，则当前线程会进入阻塞
            while (!dataUsed) {
                condition.await();
            }
            //修改数据 ，并且将 dataUsed 状态标识为 fasle、
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            shareData++;
            dataUsed = false;
            System.out.println("product the new value :" + shareData);
            //③ 通知并唤醒 在wait中 的其他线程——数据使用线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //对数据进行使用
    private static void use() {
        //获取锁，如果当前锁被其他线程持有，则当前线程会进入阻塞
        lock.lock();
        try {
            // ④ 如果当前数据已经使用，则当前线程将进入wait队列，并且释放 lock
            while (dataUsed) {
                condition.await();
            }
            //使用数据 ，并且将 dataUsed 状态标识为 true 、
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
            dataUsed = true;
            System.out.println("the shared data changed :" + shareData);
            // ⑤ 通知并唤醒wait 队列中的其他线程——修改数据
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 创建并启动两个匿名线程
        new Thread(() -> {
            for (; ; ) {
                change();
            }
        }, "Producer").start();
        new Thread(() -> {
            for (; ; ) {
                use();
            }
        }, "Consumer").start();
    }
}
