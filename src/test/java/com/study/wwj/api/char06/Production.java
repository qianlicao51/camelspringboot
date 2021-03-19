package com.study.wwj.api.char06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 15:19
 */
public class Production {
    private final String name;
    private final double price;

    public Production(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static void main(String[] args) {
        final Stream<Production> stream = Stream.of(
                new Production("T-shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d));

        //过滤，只保留衣服元素并返回一个新的 stream
        final Double totalPrice = stream.filter(p -> p.getName().equals("cloth"))
                .collect(Collectors.summingDouble(Production::getPrice));

        //
        final double cloth = stream.filter(p -> p.getName().equals("cloth"))
                .mapToDouble(Production::getPrice)
                .sum();


        //分组操作
        final List<Production> list = Arrays.asList(new Production("T-shirt", 43.34d),
                new Production("cloth", 99.99d),
                new Production("shoe", 123.8d),
                new Production("hat", 26.5d),
                new Production("cloth", 199.99d),
                new Production("shoe", 32.5d));
        final Map<String, Double> prodPrice = new HashMap<>();
        for (Production p : list) {
            final String name = p.getName();
            final double price = p.getPrice();
            if (prodPrice.containsKey(name)) {
                final Double aDouble = prodPrice.get(name);
                prodPrice.put(name, aDouble + price);
            } else {
                prodPrice.put(name, price);
            }
        }

        final Map<String, Double> collect = stream.collect(Collectors.groupingBy(
                //分组
                Production::getName,
                //
                Collectors.summingDouble(Production::getPrice)
        ));

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
