package com.study.wwj.api.char07;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.JmxAttributeGauge;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/6/6 22:32
 */
public class JmxAttributeGaugeExample {
    private final static MetricRegistry metricRegistry = new MetricRegistry();
    private final static ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertDurationsTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES).build();

    public static void main(String[] args) {
        reporter.start(10, TimeUnit.SECONDS);

        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class, "Heap Memory"), new JmxAttributeGauge())

    }
}
