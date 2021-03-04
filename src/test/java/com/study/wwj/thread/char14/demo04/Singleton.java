package com.study.wwj.thread.char14.demo04;


/**
 * @author study
 * @version 1.0
 * @date 2021/3/4 17:24
 */
//final 不允许被继承
public final class Singleton {
    private static Singleton instance = null;
    private byte[] data = new byte[1024];

    private Singleton() {
    }

    public static Singleton getInstance() {
        //当instance==null,进入同步代码块，同时该判断避免了每次都需要进入同步代码块
        //可以提高效率
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
