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
public class JMHExample02 {

    public static void main1(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder().include(JMHExample02.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .warmupIterations(10)
                .build();
        new Runner(options).run();
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample02.class.getSimpleName())
                .forks(1)
                .measurementIterations(5)//度量的执行批次为5，这5个批次的 ，将会纳入统计
                .warmupIterations(3)// 在真正的度量之前，首先会对代码进行3个批次的热身，使代码的运行达到JVM已经优化的效果
                .build();
        new Runner(opts).run();
    }

    public void normalMethod() {
    }
}
