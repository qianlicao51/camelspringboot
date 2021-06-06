package com.study.wwj.api.char07;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/6/6 22:05
 */
public class MeterExample {
    private final static MetricRegistry registry = new MetricRegistry();

    private final static Meter requestMeter = registry.meter("tqs");

    private final static Meter sizeMeter = registry.meter("volume");

    public static void main(String[] args) {
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertDurationsTo(TimeUnit.MINUTES)
                .convertDurationsTo(TimeUnit.MINUTES).build();

        reporter.start(10, TimeUnit.SECONDS);

        // 6 提供服务
        for (; ; ) {
            // 7 上传数据

            upload(new byte[ThreadLocalRandom.current().nextInt(1000)]);
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void upload(byte[] bytes) {
        requestMeter.mark();
        sizeMeter.mark(bytes.length);
    }

}
