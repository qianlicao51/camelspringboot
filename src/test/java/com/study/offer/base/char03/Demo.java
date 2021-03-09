package com.study.offer.base.char03;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 20:31
 */
public class Demo {
    public static void main(String[] args) {
//大小为100 的公平队列
        ArrayBlockingQueue fair = new ArrayBlockingQueue(100, true);
//大小为100 的非公平队列
        ArrayBlockingQueue Nofair = new ArrayBlockingQueue(100, false);
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(100);
    }
}
