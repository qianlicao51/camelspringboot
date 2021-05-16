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
public class JMHExample017 {
    private Inc inc1 = new Inc1();
    private Inc inc2 = new Inc2();

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample017.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    private int measure(Inc inc) {
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += inc.inc();
        }
        return result;
    }

    @Benchmark
    public int measure_inc_1() {
        return this.measure(inc1);
    }

    @Benchmark
    public int measure_inc_2() {
        return this.measure(inc2);
    }

    @Benchmark
    public int measure_inc_3() {
        return this.measure(inc1);
    }

    //Inc1 Inc2 完全一样
    interface Inc {
        int inc();
    }

    public static class Inc1 implements Inc {
        private int i = 0;

        @Override
        public int inc() {
            return ++i;
        }
    }

    public static class Inc2 implements Inc {
        private int i = 0;

        @Override
        public int inc() {
            return ++i;
        }
    }
}
