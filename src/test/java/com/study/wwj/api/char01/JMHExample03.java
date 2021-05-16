package com.study.wwj.api.char01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/15 19:46
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Measurement(iterations = 5)
@Warmup(iterations = 2)
public class JMHExample03 {
    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample03.class.getSimpleName())
                .forks(1)
                // .measurementIterations(2)//度量的执行批次为5，这5个批次的 ，将会纳入统计
                // .warmupIterations(1)// 在真正的度量之前，首先会对代码进行3个批次的热身，使代码的运行达到JVM已经优化的效果
                .build();
        new Runner(opts).run();
    }

    @Fork(1)
    @Benchmark
    public void test() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1);
    }

    /**
     * 预热5个批次
     * 度量10个批次
     */
    @Measurement(iterations = 10)
    @Warmup(iterations = 5)
    // @Benchmark
    public void test2() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(1);
    }
}
