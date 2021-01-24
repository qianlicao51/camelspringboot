package com.study.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/24 22:23
 */
@Slf4j(topic = "c.Test26")
public class Test26 {
    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        t1.start();

        new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        }, "t2").start();

    }
}
