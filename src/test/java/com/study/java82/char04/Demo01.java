package com.study.java82.char04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/30 15:03
 */
public class Demo01 {
    static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    public static void main(String[] args) {
        final Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian)
                .findAny();

        final int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        final IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        final Stream<Integer> stream = intStream.boxed();

        final Long howManyDishes = menu.stream().collect(Collectors.counting());
        final long count = menu.stream().count();

        //寻找最大值
        final Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);
        final Optional<Dish> collect = menu.stream().collect(Collectors.maxBy(dishComparator));

    }

    static void findAny() {

    }

    static void test3() {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        final List<String> collect = streamOfwords.map(word -> word.split(""))//将每个单词转换成由其字母构成的数组
                .flatMap(Arrays::stream)//将各个生成的扁平化 为单个流
                .distinct().collect(Collectors.toList());

    }

    static void test2() {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

//java8
        /**
         * 采用这种方式，
         */
        final List<Dish> filteredMenu = specialMenu.stream()//
                .filter(dish -> dish.getCalories() < 320)//
                .collect(Collectors.toList());

        final List<Dish> collect = filteredMenu.stream()//
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

    }

    static void test1() {
        final List<String> threeHighCaloricDishNames = menu.stream()//
                .filter(dish -> dish.getCalories() > 300)//
                .map(Dish::getName)//获取菜名
                .limit(3)//
                .collect(Collectors.toList());
        System.out.println(threeHighCaloricDishNames);


    }

}
