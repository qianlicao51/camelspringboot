package com.study.wwj.api.char01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/15 19:46
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//5个线程同时对资源进行测试
@Threads(5)
//设置为线程间共享的资源
@State(Scope.Benchmark)
public class JMHExample09 {
    private Map<Long, Long> concurrentMap;
    private Map<Long, Long> syschronizedMap;

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample09.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    @Setup
    public void setup() {
        concurrentMap = new ConcurrentHashMap<>();
        syschronizedMap = Collections.synchronizedMap(new HashMap<>());
    }

    @Benchmark
    public void testconcurrentMap() {
        this.concurrentMap.put(System.nanoTime(), System.nanoTime());
    }

    @Benchmark
    public void testsyschronizedMap() {
        this.syschronizedMap.put(System.nanoTime(), System.nanoTime());
    }
}
