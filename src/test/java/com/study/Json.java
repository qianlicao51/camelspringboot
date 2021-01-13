package com.study;

import net.sf.json.JSONObject;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/11 16:55
 */
public class Json {
    /**
     * 地感轮训-开始
     */
    public final static JSONObject POOL_START = new JSONObject();
    /**
     * 地感轮训-停止
     */
    public final static JSONObject POOL_END = new JSONObject();

    static {
        POOL_START.put("flag", "START");
        POOL_END.put("flag", "STOP");
    }


    synchronized static void synStatic() {
        System.out.println("static");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void synObj() {
        System.out.println("synObj");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // 打印:1
        System.out.println(POOL_END);
        new Thread(() -> {
            synStatic();
        }).start();
        new Thread(() -> {
            new Json().synObj();
        }).start();
    }
}
