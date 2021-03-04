package com.study.wwj.thread.char14.demo03;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/4 17:16
 */
//final 不允许被继承
public final class Singleton {

    //定义实例 但是不是直接初始化
    private static Singleton instance = null;
    //实例变量
    private byte[] data = new byte[1024];

    private Singleton() {
    }

    // 加入同步控制，每次只能有1个线程进入
    private static synchronized Singleton getInstance() {
        if (instance == null) {
        }
        return instance;
    }
}
