package com.study.wwj.thread.char17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 10:51
 */
public class ShareData {
    //定义 共享数据(资源)
    private final List<Character> container = new ArrayList<>();

    //构造ReadWriterLock
    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    //创建读锁
    private final Lock readLock = readWriteLock.readLock();
    //创建写入锁
    private final Lock writeLock = readWriteLock.writeLock();

    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add(i, 'c');
        }
    }

    public char[] read() throws InterruptedException {
        try {
            // 使用读锁
            readLock.lock();
            final char[] newBuffer = new char[length];
            for (int i = 0; i < length; i++) {
                newBuffer[i] = container.get(i);
            }
            slowly();
            return newBuffer;
        } finally {
            readLock.unlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            //使用写锁
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                this.container.add(i, c);
            }
            slowly();
        } finally {
            writeLock.unlock();
        }
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
    }

}
