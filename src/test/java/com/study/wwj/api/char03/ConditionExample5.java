package com.study.wwj.api.char03;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 17:51
 */
// 生产者 消费者
public class ConditionExample5 {
    //定义显示锁
    private static final ReentrantLock lock = new ReentrantLock();
    //创建与显示锁lock  关联的 Condition对象
    private static final Condition condition = lock.newCondition();

    //定义long 型的数据链表
    private static final LinkedList<Long> list = new LinkedList<>();
    //链表的最大容量
    private static final int CAPACITY = 100;

    //定义数据的初始值为0
    private static long i = 0;

    //生产者方法
    private static void produce() {
        //获取锁
        lock.lock();
        try {
            //链表数据大于等于100 为一个临界值，当list中的数据量达到100时，生产者线程将被阻塞加入与 condition 关联的wait队列中
            while (list.size() >= CAPACITY) {
                condition.await();
            }
            //链表的数据量不足100时，生产新的数据
            i++;
            //数据放到列队尾部
            list.addLast(i);
            System.out.println(Thread.currentThread().getName() + "->" + i);
            //① 通知其他线程
            condition.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    private static void consume() {
        lock.lock();
        try {
            while (list.isEmpty()) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "->" + list.removeFirst());
            //② 通知其他线程
            condition.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        //启动10个生产者
        IntStream.range(0, 10).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    produce();
                    sleep();
                }
            }, "Producer-" + i).start();
        });
        //启动5个消费者
        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                for (; ; ) {
                    consume();
                    sleep();
                }
            }, "Consumer-" + i).start();
        });
    }
}
