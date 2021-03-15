package com.study.wwj.thread.char22;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 16:28
 */
public class AutoSavaThread extends Thread {
    private final Document document;

    public AutoSavaThread(Document document) {
        super("DocumentAutoSaveThread");
        this.document = document;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //每隔1秒保存一次
                document.save();
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                break;
            }
        }
    }
}
