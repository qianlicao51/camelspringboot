package com.study.wwj.api.char03;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/13 22:18
 */
public class RateLimiterExample2 {
    //定义单位时间(1秒)的速率或者可用的许可证数量
    public static final RateLimiter rateLimiter = RateLimiter.create(2.0d);

    public static void main(String[] args) {
        //第一次申请4个，这样会透支下一次请求的时间
        System.out.println(rateLimiter.acquire(4));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(2));
        System.out.println(rateLimiter.acquire(2));
    }
}
