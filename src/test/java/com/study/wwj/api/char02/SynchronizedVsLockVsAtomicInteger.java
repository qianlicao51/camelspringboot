package com.study.wwj.api.char02;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: MI
 * @Date: 2022/3/13/21:57
 * @Description:
 */
//度量批次为10
@Measurement(iterations = 10)
//预热批次为10次
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
//时间单位为微秒
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SynchronizedVsLockVsAtomicInteger {
    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(SynchronizedVsLockVsAtomicInteger.class.getSimpleName())
                .forks(1)
                .timeout(TimeValue.seconds(10))
                .addProfiler(StackProfiler.class)
                .build();
        new Runner(opts).run();
    }

    //基准测试方法
    @GroupThreads(10)
    @Group("sync")
    @Benchmark
    public void syncInc(IntMonitor monitor) {
        monitor.syncInc();
    }

    @GroupThreads(10)
    @Group("lock")
    @Benchmark
    public void lockInc(IntMonitor monitor) {
        monitor.lockInc();
    }

    @GroupThreads(10)
    @Group("atomic")
    @Benchmark
    public void atomicIntegerInc(AtomicIntegerMonitor monitor) {
        monitor.inc();
    }

    @State(Scope.Group)
    public static class IntMonitor {
        private final Lock lock = new ReentrantLock();
        private int x;

        //用显示锁Lock进行共享资源同步
        public void lockInc() {
            lock.lock();
            try {
                x++;
            } finally {
                lock.unlock();
            }
        }

        public void syncInc() {
            synchronized (this) {
                x++;
            }
        }
    }

    @State(Scope.Group)
    public static class AtomicIntegerMonitor {
        private AtomicInteger x = new AtomicInteger();

        public void inc() {
            x.incrementAndGet();
        }
    }
}
