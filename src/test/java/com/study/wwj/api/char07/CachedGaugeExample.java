package com.study.wwj.api.char07;

import com.codahale.metrics.CachedGauge;
import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/7 10:23
 */
public class CachedGaugeExample {
    private final static MetricRegistry registry = new MetricRegistry();
    private final static ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertDurationsTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES).build();

    @Test
    public void start() throws InterruptedException {
        reporter.start(10, TimeUnit.SECONDS);
        registry.gauge("cached-db-size", () -> new CachedGauge<Long>(30, TimeUnit.SECONDS) {
            @Override
            protected Long loadValue() {
                return queryFromDB();
            }
        });

        Thread.currentThread().join();
    }

    private static Long queryFromDB() {
        System.out.println("=====queryFromDB====");
        return System.currentTimeMillis();
    }

}
