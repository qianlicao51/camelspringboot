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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
//设置5个线程运行基准测试方法
@Threads(5)
public class JMHExample06 {
    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample06.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    // 通过基准测试将state引用传入
    @Benchmark
    public void test(Test test) {
        test.method();
    }

    //5个线程，每一个线程都会持有一个test实例
    @State(Scope.Thread)
    public static class Test {
        public Test() {
            System.out.println("create instance");
        }

        public void method() {
        }
    }
}
