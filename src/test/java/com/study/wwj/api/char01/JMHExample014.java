package com.study.wwj.api.char01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
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
public class JMHExample014 {

    double x1 = Math.PI;
    double x2 = Math.PI * 2;

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample014.class.getSimpleName())
                .build();
        new Runner(opts).run();
        int sum = 0;
        for (int i = 0; i < 20; i += 5) {
            sum += i;
            sum += i + 1;
            sum += i + 2;
            sum += i + 3;
            sum += i + 4;
        }
    }

    @Benchmark
    public double baseline() {
        return Math.pow(x1, 2);
    }

    @Benchmark
    public double powButReturnOne() {
        //Dead Code 会被擦除
        Math.pow(x1, 2);
        //不会别擦除，因为返回结果
        return Math.pow(x2, 2);
    }

    @Benchmark
    public double powThenAdd() {
        return Math.pow(x1, 2) + Math.pow(x1, 2);
    }

    @Benchmark
    public void useBlackhole(Blackhole hole) {
        //将结果存放到 black hole，因此两次pow操作都会生效
        hole.consume(Math.pow(x1, 2));
        hole.consume(Math.pow(x2, 2));
    }

}
