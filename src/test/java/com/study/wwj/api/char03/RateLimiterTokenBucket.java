package com.study.wwj.api.char03;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 9:15
 */
//令牌环桶 模拟商品抢购
public class RateLimiterTokenBucket {
    //当前活动商品数量
    public static final int MAX = 100;
    //单位时间内只允许10个用户能够抢购到商品，也就是说订单服务会被匀速地调用
    private final RateLimiter rateLimiter = RateLimiter.create(10.0D);
    //订单编号，订单成功之后会产生一个新的订单
    private int orderID;
    private Monitor bookOrderMonitor = new Monitor();

    // 前台用户下单，但是只允许云速地进行订单服务调用
    public void bookOrder(Consumer<Integer> consumer) throws NoProductionException, OrderFailedException {
        //如果当前商品有库存则进行抢购操作
        if (bookOrderMonitor.enterIf(bookOrderMonitor.newGuard(() -> orderID < MAX))) {
            try {
                //抢购商品，最多等待100毫秒
                if (!rateLimiter.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                    //如果在100 毫秒抢购失败，抛出订购失败异常，客户端可以重新尝试操作
                    throw new OrderFailedException("book order failed,please try again later.");
                }
                //执行订单订购操作
                orderID++;
                consumer.accept(orderID);
            } finally {
                bookOrderMonitor.leave();
            }
        } else {
            //当前商品已经没有库存，则抛出没有商品的异常，该异常不会再次进行尝试
            throw new NoProductionException("No avaliable production now.");
        }
    }

    //商品售罄的时候抛出异常
    static class NoProductionException extends Exception {
        public NoProductionException(String message) {
            super(message);
        }
    }

    static class OrderFailedException extends Exception {
        public OrderFailedException(String message) {
            super(message);
        }
    }
}
