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
public class JMHExample012 {
    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample012.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    //禁止优化
    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test1() {
    }

    @CompilerControl(CompilerControl.Mode.EXCLUDE)
    @Benchmark
    public void test2() {
        Math.log(Math.PI);
    }
}
