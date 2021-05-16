package com.study.wwj.api.char01;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
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
@State(Scope.Benchmark)
public class JMHExample011 {
    private List<String> list;

    public static void main(String[] args) throws RunnerException {
        final Options opts = new OptionsBuilder().include(JMHExample011.class.getSimpleName())
                .jvmArgs("-ea")//激活断言 enable assertion的意思
                .build();
        new Runner(opts).run();
    }

    //将方法标记为setup，执行初始化操作
    @Setup(Level.Invocation)
    public void setup() {
        this.list = new ArrayList<>();
    }

    @Benchmark
    public void measureRight() {
        this.list.add("test");
    }

    @Benchmark
    public void measureWrong() {
        //do nothing
    }

    //运行资源回收甚至断言的操作
    @TearDown
    public void tearDown() {
        assert this.list.size() > 0 : "the list elements must greatr than zero";
    }
}
