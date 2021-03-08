package com.study.wwj.thread.char17;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 10:18
 */
public class WriteLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                //首先使等待写入锁的数字加一
                readWriteLock.incrementWaitingWriters();
                //如果有其他线程正在进行读操作，或者写操作，那么当前线程将被挂起
                while (readWriteLock.getReadingReaders() > 0
                        || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                //成功获取了写入锁，使得等待获取写入锁的计数器减一
                readWriteLock.decrWaitingWriters();
            }
            //将正在写入的线程数量加一
            readWriteLock.incrementWritingWriters();
        }
    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()) {
            // 减少正在写入锁的计数器
            readWriteLock.decrWritingWriters();
            //将偏好锁状态修改为false，可以使得读锁被最快的获取
            readWriteLock.changePrefer(false);
            //通知唤醒其他在 Mntex monitor waitset 中的 线程
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
