package com.study.wwj.thread.char17;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 9:45
 */
public class ReadWriteLockImpl implements ReadWriteLock {
    private final Object MUTEX = new Object();
    //当前有多少个线程正在写入
    private int writingWrties = 0;
    // 当前有多少个线程正在等待写入
    private int waitingWriters = 0;
    // 当前有多少个线程正在read
    private int readingReaders = 0;
    //read 和write 的偏好设置
    private boolean preferWriter;
    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
    public ReadWriteLockImpl() {
        this(true);
    }
    public Object getMUTEX() {
        return MUTEX;
    }
    public boolean isPreferWriter() {
        return preferWriter;
    }
    //创建 write lock
    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }
    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }
    //使写的数量增加
    void incrementWritingWriters() {
        this.waitingWriters++;
    }
    // 使等待 写入的数量增加
    void incrementWaitingWriters() {
        this.waitingWriters++;
    }
    //使读的线程数增加
    void incrReadingReaders() {
        this.readingReaders++;
    }
    //使写线程的数量减少
    void decrWritingWriters() {
        this.writingWrties--;
    }
    void decrWaitingWriters() {
        this.waitingWriters--;
    }
    //使读线程的数量减少
    void decrReadingReaders() {
        this.readingReaders--;
    }
    //获取 当前有多少个 正在进行写操作
    public int getWritingWrties() {
        return writingWrties;
    }
    public int getReadingReaders() {
        return readingReaders;
    }
    @Override
    public int getWritingWriters() {
        return this.writingWrties;
    }
    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }
    @Override
    public int gerReadingReaders() {
        return this.readingReaders;
    }
    //设置偏向锁
    void changePrefer(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
