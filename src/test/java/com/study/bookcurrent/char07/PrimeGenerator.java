package com.study.bookcurrent.char07;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/2 14:19
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {

    //GuardedBy https://blog.csdn.net/chirousha4455/article/details/100858745
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

    /**
     * 使用 volatile 域 保存取消状态
     */
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            System.out.println(p);
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }

    /**
     * 生成素数的程序运行一秒钟
     */
    List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        final PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
