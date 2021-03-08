package com.study.wwj.thread.char17;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 9:41
 */
public interface ReadWriteLock {
    // 工厂方法，创建 ReadWriteLock
    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }

    //创建reader 锁
    Lock readLock();
    //创建 write 锁
    Lock writeLock();

    //获取当前有多少线程正在执行写操作，最多是1个。
    int getWritingWriters();

    //获取 当前 线程有 多少线程等待获取写锁 而导致阻塞
    int getWaitingWriters();

    //获取当前 有多少 线程正等待获取 reader锁
    int gerReadingReaders();
}
