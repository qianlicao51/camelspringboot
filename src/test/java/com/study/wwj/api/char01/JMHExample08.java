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
public class JMHExample08 {
    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample08.class.getSimpleName())
                .build();
        new Runner(opts).run();
    }

    //  在线程组test中，有三个线程将不断地对test实例的write方法进行调用
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testwrite(Test test) {
        test.write();
    }

    //  在线程组test中，有三个线程将不断地对test实例的write方法进行调用
    @GroupThreads(3)
    @Group("test")
    @Benchmark
    public void testRead(Test test) {
        test.read();
    }

    // 设置为线程组共享
    @State(Scope.Group)
    public static class Test {
        public Test() {
            System.out.println("create instance");
        }

        public void write() {
            System.out.println("write");
        }

        public void read() {
            System.out.println("read");
        }
    }
}
