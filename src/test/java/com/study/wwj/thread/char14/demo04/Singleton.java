package com.study.wwj.thread.char14.demo04;


import java.net.Socket;
import java.sql.Connection;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/4 17:24
 */
//final 不允许被继承
public final class Singleton {
    private volatile static Singleton instance = null;
    private byte[] data = new byte[1024];

    Connection conn;
    Socket socket;

    private Singleton() {
        //初始化 conn
        // this.conn =
        //初始化 socket
        this.socket = new Socket();
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
