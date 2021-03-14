package com.study.wwj.api.char03;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 9:28
 */
public class RateLimiterExample4 {
    private static final RateLimiterTokenBucket tokenBucket = new RateLimiterTokenBucket();

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                while (true) {
                    //抢购商品
                    try {
                        tokenBucket.bookOrder(proID -> System.out.println("user:" + Thread.currentThread().getName() + " book the prod order and prodID:" + proID));
                    } catch (RateLimiterTokenBucket.NoProductionException e) {
                        //商品已经售罄，退出抢购
                        System.out.println("all of production already sold out");
                        break;
                    } catch (RateLimiterTokenBucket.OrderFailedException e) {
                        //抢购失败，重新尝试抢购
                        System.out.println("user:" + Thread.currentThread().getName() + " book order failed,will try again");
                    }
                }
            }).start();
        }
    }
}
