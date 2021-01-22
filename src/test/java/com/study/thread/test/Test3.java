package com.study.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/22 14:38
 */
@Slf4j(topic = "c.Test3")
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        final TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        TimeUnit.SECONDS.sleep(2);
        tpt.stop();
    }

    private static void test03() {
        new Thread(() -> {

        }, "t1").start();

    }

    /**
     * Sleep会清除打断标志
     *
     * @throws InterruptedException
     */
    public void test() throws InterruptedException {
        final Thread thread = new Thread(() -> {
            while (true) {
                try {
                    // Thread.sleep(100);
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().isInterrupted());

            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination {
    private Thread monitor;

    /**
     * 两阶段终止方式
     */
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                final Thread currentThread = Thread.currentThread();
                if (currentThread.isInterrupted()) {
                    log.debug("料理 最后");
                    break;
                }
                try {
                    Thread.sleep(TimeUnit.SECONDS.toSeconds(1));
                    log.info("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //重新设置打断标志；
                    currentThread.interrupt();
                }
            }
        });
        monitor.start();
    }

    public void stop() {
        monitor.interrupt();
    }
}
