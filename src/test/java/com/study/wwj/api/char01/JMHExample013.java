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
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//设置为线程间共享的资源
@State(Scope.Thread)
public class JMHExample013 {
    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample013.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    /**
     * 空方法，主要用于做基准数据
     */
    @Benchmark
    public void baseline() {
    }

    /**
     * 虽然进行了log运算，但是结果既没有再进行二次使用，没有返回
     */
    @Benchmark
    public void measureLog1() {
        //进行数学运算，但是在局部方法内
        Math.log(Math.PI);
    }

    /**
     * 第二次的结果没有更进一步的使用
     */
    @Benchmark
    public void measureLog2() {
        double result = Math.log(Math.PI);
        Math.log(result);
    }

    /**
     * 结果进行了返回操作
     *
     * @return
     */
    @Benchmark
    public double measureLog3() {
        return Math.log(Math.PI);
    }
}
