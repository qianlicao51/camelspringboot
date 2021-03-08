package com.study.wwj.thread.char17;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 10:12
 */
public class ReadLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        // 使用Mutex 作为锁
        synchronized (readWriteLock.getMUTEX()) {
            //  若 此时有线程正在进行写操作，或者有线程正在等待偏向锁的表示为true，就会无法获得锁，只能被挂起。
            while (readWriteLock.getWritingWriters() > 0
                    || (readWriteLock.isPreferWriter()
                    && readWriteLock.getWaitingWriters() > 0)) {

                readWriteLock.getMUTEX().wait();
            }
            //成功获取锁，使得读的数量 增加
            readWriteLock.incrReadingReaders();
        }
    }

    @Override
    public void unlock() {
        // 使用Mutex 作为锁，并且进行同步
        synchronized (readWriteLock.getMUTEX()) {
            //释放锁的过程 就是使得当前 reading 的数量减一
            //将perferWriter 设置为true ，可以使得writer线程获得更多的机会。
            //通知唤醒与 Mutex 关联Monitor waitset中的数量
            readWriteLock.decrReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
