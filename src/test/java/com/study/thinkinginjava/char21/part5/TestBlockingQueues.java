package com.study.thinkinginjava.char21.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/20 21:05
 */
class LiftOff implements Runnable {
    private static int taskCount = 0;
    private final int id = taskCount++; //区分任务的多个实例
    protected int countDown = 10; // Default

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            //是对线程调度器的一种建议，"我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机。"
            Thread.yield();
        }
    }
}

class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> rockets;

    public LiftOffRunner(BlockingQueue<LiftOff> rockets) {
        this.rockets = rockets;
    }

    public void add(LiftOff lo) {
        rockets.add(lo);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                final LiftOff rocket = rockets.take();
                rocket.run();
            }
        } catch (InterruptedException e) {
            System.out.println("waking from take()");
        }
        System.out.println("exiting liftoffrunner");
    }
}

public class TestBlockingQueues {
    static void getkey() {
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getkey(String message) {
        System.out.println(message);
        getkey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        final LiftOffRunner runner = new LiftOffRunner(queue);
        final Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++) {
            runner.add(new LiftOff(5));
        }
        getkey("press enter ( " + msg + " )");
        t.interrupt();
        System.out.println("finished " + msg + " test ");

    }

    public static void main(String[] args) {
        test("LinkedBlock", new LinkedBlockingDeque<LiftOff>());
    }
}
