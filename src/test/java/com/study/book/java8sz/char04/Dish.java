package com.study.book.java8sz.char04;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/20 11:19
 */
@Getter
@Setter
public class Dish {
    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public static void test() {
        final List<String> threeHighCaloricDishNames = menu.stream()
                // 首先选出高热量的菜肴
                .filter(dish -> dish.getCalories() > 300)
                //获取菜名
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        //只选头三个
        System.out.println(threeHighCaloricDishNames);
    }

    public static void main(String[] args) {
        test();
    }

    static void forEach() {
        //集合：用 for-each 循环外部迭代
        final ArrayList<String> names = new ArrayList<>();
        //显示顺序迭代菜单列表
        for (Dish dish : menu) {
            //提取名称并将其添加到累加器
            names.add(dish.getName());
        }
        //集合：用背后的迭代器做外部迭代
        final Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            final Dish dish = iterator.next();
            names.add(dish.getName());
        }

        // 流：内部迭代
        final List<String> streamNames = menu.stream()
                .map(Dish::getName)
                // 开始执行操作流水线：没有迭代
                .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .toString();
    }

    public enum Type {MEAT, FISH, OTHER}
}
