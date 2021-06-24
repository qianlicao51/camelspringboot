package com.study.wwj.api.char07;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.RatioGauge;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/7 10:12
 */
public class RatioGaugeExample {
    private final static MetricRegistry registry = new MetricRegistry();
    private final static ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertDurationsTo(TimeUnit.MINUTES)
            .convertDurationsTo(TimeUnit.MINUTES).build();

    private final static Meter totalMeter = new Meter();
    private final static Meter successMeter = new Meter();

    @Test
    public void start() {
        reporter.start(10, TimeUnit.SECONDS);
        registry.gauge("success-rate", () -> new RatioGauge() {
            @Override
            protected Ratio getRatio() {
                return Ratio.of(successMeter.getCount(), totalMeter.getCount());
            }
        });
        //无限循环 ，模拟程序持续服务
        for (; ; ) {
            //短暂休眠
            shortSleep();
            //受理业务
            business();
        }

    }

    private static void business() {
        // 无论正确与否，total都会自增
        // total inc
        totalMeter.mark();
        try {
            //随机数可能为0 ，因此这个操作可能会失败
            int x = 10 / (ThreadLocalRandom.current().nextInt(5));
            successMeter.mark();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
