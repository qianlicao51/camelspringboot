package com.study;

import java.util.LinkedList;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/13 9:27
 */
public class ArrlyQueue {

    private final LinkedList<String> list;
    private int size;

    public ArrlyQueue(int size) {
        this.size = size;
        list = new LinkedList<>();
    }

    public synchronized boolean empty() {
        return list.isEmpty();
    }

    public synchronized boolean full() {
        return list.size() == size;

    }

    public void put(String str) {
        synchronized (list) {
            if (list.size() >= size) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.list.addLast(str);
            this.list.notify();
        }
    }

    public String get() {
        synchronized (list) {
            if (list.isEmpty()) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final String s = list.removeFirst();
            this.list.notify();
            return s;
        }
    }

}
