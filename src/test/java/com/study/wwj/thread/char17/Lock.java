package com.study.wwj.thread.char17;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 9:30
 */

/**
 * Lock接口定了了锁的基本操作，加锁和解锁，显示锁的操作强烈建议
 * 与try finally语句块一起使用，
 */
public interface Lock {
    //获取显示锁，没有获得锁的线程将被阻塞
    void lock() throws InterruptedException;

    //释放锁
    void unlock();


}
