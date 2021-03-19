package com.study.wwj.thread.char23;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 16:23
 */
public class PorgrammerWaitTimeout {
    public static void main(String[] args) throws InterruptedException {
        final CountDwonLath latch = new CountDwonLath(4);
        new ProgrammerTravel(latch, "Alex", "Bus").start();
        new ProgrammerTravel(latch, "Tom", "Walking").start();
        new ProgrammerTravel(latch, "Jack", "Subway").start();
        new ProgrammerTravel(latch, "sl", "Bicycle").start();

        try {
            latch.await(TimeUnit.SECONDS, 2);
            System.out.println("=====all of programmer arrive ");
        } catch (WaitTimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(" all of programmer arrive ");

    }
}
