package com.study.wwj.thread.char23;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/15 17:15
 */
public class ProgrammerTravel extends Thread {
    //门阀
    private final Lathch lathch;
    //程序员
    private final String programmer;
    //交通工具
    private final String transportation;

    public ProgrammerTravel(Lathch lathch, String programmer, String transportation) {
        this.lathch = lathch;
        this.programmer = programmer;
        this.transportation = transportation;
    }

    public static void main(String[] args) throws InterruptedException {
        final CountDwonLath latch = new CountDwonLath(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Tom", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "sl", "Bicycle").start();

        latch.await();
        System.out.println(" all of programmer arrive ");

    }

    @Override
    public void run() {
        System.out.println(programmer + " start take the transportation:" + transportation);
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(programmer + " arrived by " + transportation);
        //计数器减一
        lathch.countDown();
    }
}
