package com.study.wwj.api.char03;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 21:58
 */
public class RateLimiterExample1 {
    //定义一个 Rate Limiter
    private static RateLimiter rateLimiter = RateLimiter.create(0.5);

    public static void main(String[] args) {
        for (; ; ) {
            testRateLimiter();
        }
    }

    //该方法只能每2秒执行一次
    private static void testRateLimiter() {
        //在访问时间之前首先要进行 rateLimiter的获取，返回值为实际的获取等待开销时间
        final double elapsedSecond = rateLimiter.acquire();
        System.out.println(Thread.currentThread().getName() + ": elapsed seconds: " + elapsedSecond);
    }
}
