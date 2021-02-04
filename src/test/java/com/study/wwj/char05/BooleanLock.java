package com.study.wwj.char05;

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
     * 存放哪些线程获取房当前锁时进入阻塞队列
     */
    private final List<Thread> blockedList = new ArrayList<>();
    /**
     * 当前用有所的线程
     */
    private Thread currentThread;
    /**
     * false表示当前锁没有被任何线程获得
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
                Optional.of(currentThread.getName() + " releanse the lock")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
