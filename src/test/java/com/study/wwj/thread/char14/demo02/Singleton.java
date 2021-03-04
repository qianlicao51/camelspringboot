package com.study.wwj.thread.char14.demo02;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/4 15:40
 */
//final 不允许被继承
public final class Singleton {
    //定义实例 但是不是直接初始化
    private static Singleton instance = null;
    //实例变量
    private byte[] data = new byte[1024];

    private Singleton() {
    }

    private static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

}
