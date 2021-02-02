package com.study.java82.char16;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/31 11:03
 */
public class Shop {
    private final static Random random = new Random(System.currentTimeMillis());
    static List<Shop> shops = List.of(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );
    private String shopName;

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static List<String> findPirce(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.shopName, shop.getPrice(product))).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        extracted();

    }

    /**
     * 这里使用两个不同的Stream流水线，而不是在同一个处理流的流水线上一个接一个放置两个map操作，其实是有缘故的
     * 考虑流操作之间的延迟特性，如果在单一流水线中处理流，那么发向不同商家的请求只能以同步、顺序执行的方式才会成功
     */
    public static List<String> findPirces(String product) {
        //使用异步计算商品价格
        final List<CompletableFuture<String>> priceFuture = shops.stream()//
                .map(shop -> CompletableFuture.supplyAsync(() //
                        -> shop.shopName + " price is " +
                        +shop.getPrice(product))).collect(Collectors.toList());
        //等待所有操作都结束
        return priceFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 4个方法是顺序执行的。
     */
    private static void extracted() {
        final long start = System.nanoTime();
        // System.out.println(findPirce("myPhone27S"));
        System.out.println(findPirces("myPhone27S"));
        final long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation return after " + invocationTime + " mescs");
    }

    public static void main1(String[] args) {
        final Shop shop = new Shop("BestShop");
        final long start = System.nanoTime();
        final Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
        final long invocationTime = (System.nanoTime() - start) / 1_000_000;

        System.out.println("Invocation return after " + invocationTime + " mescs");

        try {
            final Double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        final long retrievalTime = (System.nanoTime() - start) / 1_000_000;

        System.out.println("Price return after " + retrievalTime + " mescs");


    }

    public Future<Double> getPriceAsync(String product) {
        final CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                //抛出异常，完成这次future操作。
                futurePrice.completeExceptionally(e);
            }
        }).start();
        //无需等待还没有结束的计算，直接返回future对象
        return futurePrice;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public String getPrice2(String product) {
        final double price = calculatePrice(product);
        final Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", shopName, price, code);
    }

    public double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
