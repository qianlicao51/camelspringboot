package com.study.wwj.api.char07;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/6/6 22:21
 */
public class SimpleGuageExample {
    private final static MetricRegistry metricRegistry = new MetricRegistry();
    private final static ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES).build();

    // 定义一个双向队列,这个队列是需要监控的队列
    private static final BlockingDeque<Long> queue = new LinkedBlockingDeque<>(1_000);

    private static void randomSleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void start() throws InterruptedException {
        metricRegistry.register(MetricRegistry.name(SimpleGuageExample.class, "queue-size"), (Gauge<Integer>) queue::size);
        reporter.start(1, TimeUnit.SECONDS);
        new Thread(() -> {
            for (; ; ) {
                randomSleep();
                queue.add(System.nanoTime());
            }
        }).start();
        final Thread thread = new Thread(() -> {
            for (; ; ) {
                randomSleep();
                queue.poll();
            }
        });

        thread.start();
        thread.join();
    }
}
