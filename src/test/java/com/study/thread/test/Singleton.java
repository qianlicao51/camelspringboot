package com.study.thread.test;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/27 20:19
 */
public class Singleton {
    //
    private static volatile Singleton INSTANCE = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
}
