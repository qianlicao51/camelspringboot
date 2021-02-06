package com.study.thread.b.char04;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/6 13:54
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        final Thread sleepRunner = new Thread(new SleepRunner(), "SleepRunner");
        sleepRunner.setDaemon(true);

        final Thread busyRunner = new Thread(new BusyRunner(), "BusyRunner");
        busyRunner.setDaemon(true);

        sleepRunner.start();
        busyRunner.start();

        TimeUnit.SECONDS.sleep(5);
        busyRunner.interrupt();
        sleepRunner.interrupt();

        System.out.println("sleepRunner interrupted is " + sleepRunner.isInterrupted());
        System.out.println("busyRunner interrupted is " + busyRunner.isInterrupted());

        SleepUtils.senond(2);
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.senond(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }


}

