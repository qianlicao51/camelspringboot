package com.study.thread.n3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/24 18:42
 */
@Slf4j(topic = "c.TestMultiLock")
public class TestMultiLock {
    public static void main(String[] args) {
        final BigRoom bigRoom = new BigRoom();
        new Thread(() -> {
            bigRoom.sleep();
        }, "小南").start();

        new Thread(() -> {
            bigRoom.study();
        }, "小女 ").start();
    }
}

@Slf4j(topic = "c.BigRoom")
class BigRoom {
    private final Object study = new Object();
    private final Object sleepRoom = new Object();

    public void sleep() {
        synchronized (sleepRoom) {
            log.debug("sleep 2h");
            slp(2);
        }
    }

    public void study() {
        synchronized (study) {
            log.debug("study 1h");
            slp(1);
        }
    }

    public void slp(int sleepTime) {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
