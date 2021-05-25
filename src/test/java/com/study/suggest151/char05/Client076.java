package com.study.suggest151.char05;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/24 23:21
 */
public class Client076 {
    public static void main(String[] args) {
        //集合 交集 并集 差集
        final ArrayList<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        final ArrayList<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("B");

        //并集
        list1.addAll(list2);
        System.out.println(Arrays.toString(list1.toArray()));
        //交集
        list1.retainAll(list2);

        //差集
        list1.remove(list2);

        //无重复的并集
        list2.remove(list1);
        list1.addAll(list2);
    }
}
