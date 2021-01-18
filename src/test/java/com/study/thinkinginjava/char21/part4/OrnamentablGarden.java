package com.study.thinkinginjava.char21.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/16 11:21
 */
class Count {
    private int count = 0;
    private Random random = new Random(47);

    public synchronized int increment() {
        int temp = count;
        if (random.nextBoolean()) {
            Thread.yield();
        }
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<Entrance>();
    private static volatile boolean cancelled = false;
    private final int id;
    private int number = 0;

    public Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    /**
     * cancelled是一个volatile的布尔标志，而它只是会被读取和赋值(不会与其他域组合在一起被读取)，
     * 所以不需要同步对其的访问，就可以安全地操作它
     */
    public static void cancel() {
        cancelled = true;
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance entrance : entrances) {
            sum += entrance.getValue();
        }
        return sum;
    }

    @Override
    public void run() {
        while (!cancelled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " total :" + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Stopping ");
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + " : " + getValue();
    }
}

public class OrnamentablGarden {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(1);
        Entrance.cancel();
        exec.shutdown();
        //ExecutorService.awaitTermination等待每个任务结束 ，如果所有的任务在超时时间达到之前全部结束，则返回true，否则返回false，
        //表示不是所有的任务都已经结束了
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("Some tasks were not terminated");
        }
        System.out.println("Total " + Entrance.getTotalCount());
        System.out.println("Sum of Entrances: " + Entrance.sumEntrances());
    }
}
