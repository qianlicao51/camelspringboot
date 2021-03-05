package com.study.wwj.thread.char14.demo05;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 10:02
 */
public final class Singleton {
    private byte[] data = new byte[1024];

    private Singleton() {
    }

    //调用getInstance 实际上是获得Holder的instance静态属性。
    public static Singleton getInstance() {
        return Holder.instacne;
    }

    //在静态内部类中持有 Singleton实例，并且可以被直接初始化
    private static class Holder {
        private static Singleton instacne = new Singleton();
    }
}
