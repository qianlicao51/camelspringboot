package com.study.wwj.api.char07;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.JmxAttributeGauge;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
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

    public static void main(String[] args) throws MalformedObjectNameException, InterruptedException {
        reporter.start(10, TimeUnit.SECONDS);

        // 注册 JmxAttributeGauge 主要输出堆内存使用情况
        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class, "Heap Memory"),
                new JmxAttributeGauge(new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage"));

        //注册 JmxAttributeGauge 主要输出非堆内存的使用情况
        metricRegistry.register(MetricRegistry.name(JmxAttributeGaugeExample.class, "NonHeapMemoryUsage"),
                new JmxAttributeGauge(new ObjectName("java.lang:type=Memory"), "NonHeapMemoryUsage"));

        // 目的是不让主线程退出
        Thread.currentThread().join();
    }
}
