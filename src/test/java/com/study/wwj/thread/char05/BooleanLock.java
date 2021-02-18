package com.study.wwj.thread.char05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 17:05
 */
public class BooleanLock implements Lock {
    /**
     * 存放哪些线程获取当前锁时进入阻塞队列
     */
    private final List<Thread> blockedList = new ArrayList<>();
    /**
     * 当前用有所的线程
     */
    private Thread currentThread;
    /**
     * false表示当前锁没有被任何线程获得
     * true 表示该锁已经被某个线程获得,该线程就是 currentThread
     */
    private boolean locked = false;

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                if (!blockedList.contains(Thread.currentThread())) {
                    blockedList.add(Thread.currentThread());
                }
                this.wait();
            }
            //如果当前线程从未 进入阻塞队列，删除方法不会有任何赢影响。
            //如果 当前线程是从 wait set 中被唤醒的，测需要从阻塞队列中间自己删除
            blockedList.remove(Thread.currentThread());
            this.locked = true;
            //记录获取锁的线程
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {
                //等待的毫秒数，最初由其他线程传入，但是在多次wait过程中会重新计算。
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock");
                    }
                    if (!blockedList.contains(Thread.currentThread())) {
                        blockedList.add(Thread.currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                this.locked = false;
                Optional.of(currentThread.getName() + " release the lock")
                        .ifPresent(System.out::println);
                //这里使用 notify也可以
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        //如果直接返回 blockedList ，对我可以修改这个实例，不安全
        return Collections.unmodifiableList(blockedList);
    }

    @Override
    public int getBlockedSize() {
        return blockedList.size();
    }
}
