package com.study;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/24 23:39
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(1)
@Measurement(iterations = 5)
@Warmup(iterations = 2)
public class TestForeachAndFor100W {
    public List<String> list100w = Collections.nCopies(1_000_000, "A");

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(TestForeachAndFor100W.class.getSimpleName())
                .forks(1)
                // .measurementIterations(2)//度量的执行批次为5，这5个批次的 ，将会纳入统计
                // .warmupIterations(1)// 在真正的度量之前，首先会对代码进行3个批次的热身，使代码的运行达到JVM已经优化的效果
                .build();
        new Runner(opts).run();
    }

    @Benchmark
    public void testFor_100w() {
        for (String s : list100w) {
            System.out.print(s);
        }
    }

    @Benchmark
    public void testForEach_100w() {
        list100w.forEach(item -> {
            System.out.print(item);
        });
    }

    @Benchmark
    public void testStreamForEach_100w() {
        list100w.stream().forEach(item -> {
            System.out.print(item);
        });
    }
}

