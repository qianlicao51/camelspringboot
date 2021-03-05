package com.study.wwj.thread.char14.demo07;


/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 14:01
 */
//枚举本身是 final ，不允许被继承的
public enum Singleton {
    INSTANCE;
    //实例变量
    private byte[] data = new byte[1024];

    Singleton() {
        System.out.println("INSTANCE will be initialized immediately");
    }

    public static void method() {
        //调用该方法则会主动使用Singleton,INSTANCE 将会被实例化
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
