package com.study.wwj.thread.char14.demo01;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/4 14:32
 */
//饿汉式
public final class Singleton {
    //在定义实例对象的时候直接初始化
    private static Singleton instance = new Singleton();
    //实例变量
    private byte[] data = new byte[1024];

    //私有构造函数，不允许外部new
    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
