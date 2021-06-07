package com.study.wwj.api.char07;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/7 10:33
 */
public class HistogramExample {
    private final static MetricRegistry registry = new MetricRegistry();
    private final static ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertDurationsTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES).build();

    private final static Histogram histogram = registry.histogram("search-result");

    public static void main(String[] args) {
        reporter.start(10, TimeUnit.SECONDS);

        while (true) {
            doSearch();
            shortSleep();

        }

    }

    private static void doSearch() {
        //搜索结果从随机数获得0~9 之间的结果条目
        histogram.update(ThreadLocalRandom.current().nextInt(10));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
