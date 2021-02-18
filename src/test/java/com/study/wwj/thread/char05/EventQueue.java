package com.study.wwj.thread.char05;

import java.util.LinkedList;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 15:39
 */
public class EventQueue {
    private final static int DEFAULT_MAX_EVENT = 10;
    private final int max;
    private final LinkedList<Event> eventQueue = new LinkedList<>();

    public EventQueue() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() > max) {
                try {
                    console(" the queue is full.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new event is submitted ");
            eventQueue.addLast(event);
            //  IllegalMonitorStateException
            // eventQueue.notify();
            eventQueue.notifyAll();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            //单线程变多线程通信：只需要将临界值 的判断if更改为while，将notify更改为notifyAll即可。
            while (eventQueue.isEmpty()) {
                try {
                    console(" the queue is empty");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            final Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console(" the event " + event + " is handled.");
            return event;
        }
    }

    private void console(String msg) {
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), msg);
    }

    static class Event {

    }
}
