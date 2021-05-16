package com.study.wwj.api.char01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/15 19:46
 */
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//5个线程同时对资源进行测试
@Threads(5)
//设置为线程间共享的资源
@State(Scope.Benchmark)
public class JMHExample010 {

    //为type 提供了 四种可配置爱的参数值
    @Param({"1", "2", "3", "4"})
    private int type;
    private Map<Long, Long> map;

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample010.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    @Setup
    public void setup() {
        switch (type) {
            case 1:
                this.map = new ConcurrentHashMap<>();
                break;
            case 2:
                this.map = new ConcurrentSkipListMap<>();
                break;
            case 3:
                this.map = new Hashtable<>();
                break;
            case 4:
                this.map = Collections.synchronizedMap(new HashMap<>());
                break;
            default:
                throw new IllegalArgumentException("Illegal map type");
        }
    }

    //只需要一个基准方法即可
    @Benchmark
    public void test() {
        this.map.put(System.nanoTime(), System.nanoTime());
    }
}
