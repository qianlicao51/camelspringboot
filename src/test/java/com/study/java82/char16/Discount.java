package com.study.java82.char16;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/31 12:45
 */
public class Discount {
    static List<Shop> shops = List.of(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is" +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    public static double apply(double price, Code code) {
        Shop.delay();
        return price * (100 - code.percentage) / 100;
    }

    public static List<String> findPrice(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice2(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        final long start = System.nanoTime();
        System.out.println(findPrice("myPhone27S"));
        final long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation return after " + invocationTime + " mescs");
    }

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
}
