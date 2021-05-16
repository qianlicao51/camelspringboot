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
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
//设置为线程间共享的资源
@State(Scope.Thread)
public class JMHExample016 {

    private int x = 1;
    private int y = 2;

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample016.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    @Benchmark
    public int measure() {
        return (x + y);
    }

    private int loopCpmpute(int times) {
        int result = 0;
        for (int i = 0; i < times; i++) {
            result += (x + y);
        }
        return result;
    }

    @OperationsPerInvocation
    @Benchmark
    public int measureLoop_1() {
        return loopCpmpute(1);
    }

    @OperationsPerInvocation
    @Benchmark
    public int measureLoop_10() {
        return loopCpmpute(10);
    }

    @OperationsPerInvocation
    @Benchmark
    public int measureLoop_100() {
        return loopCpmpute(100);
    }

    @OperationsPerInvocation
    @Benchmark
    public int measureLoop_1000() {
        return loopCpmpute(1000);
    }

}
