package com.study.suggest151.char06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/24 20:44
 */
public class Client065 {
    public static void main1(String[] args) {
        int[] data = {1, 2, 3, 4, 5};
        final List lis = Arrays.asList(data);
        System.out.println("列表中的元素：" + lis.size());//1
    }

    public static void main2(String[] args) {
        //定义一个包含量给字符串的列表
        final List<String> c = new ArrayList<>();
        c.add("A");
        c.add("B");

        //构造一个包含c列表的字符串列表
        final List<String> c1 = new ArrayList<>(c);
        final List<String> c2 = c.subList(0, c.size());
        c2.add("C");
        System.out.println("c==c1" + c.equals(c1));
        System.out.println("c==c2" + c.equals(c2));//true
    }

    public static void main3(String[] args) {
        //初始化一个固定长度，不可变列表
        final List<Integer> initData = Collections.nCopies(100, 0);
        //转换为可变列表
        final ArrayList<Integer> list = new ArrayList<>(initData);
        //删除指定范围元素
        list.subList(20, 30).clear();
    }

    public static void main5(String[] args) {
        final List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        final List<String> subList = list.subList(0, 2);
        list.add("D");
        System.out.println("原列表长度：" + list.size());
        //ConcurrentModificationException
        System.out.println("子列表长度：" + subList.size());

    }

    public static void main6(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        final List<String> subList = list.subList(0, 2);
        list = Collections.unmodifiableList(list);
        //对list进行只读操作
        // doReadSomething(list);
        //对subList进行读写操作
        // doReadAndWriteSomething(subList);
    }

    public static void main(String[] args) {
        final List<String> citys = new ArrayList<>();
        citys.add("上海");
        citys.add("广州");
        citys.add("广州");
        citys.add("北京");
        citys.add("天津");

        final int index1 = citys.indexOf("广州");
        final int index2 = Collections.binarySearch(citys, "广州");
        System.out.println("索引值（indexof）:" + index1);//索引值（indexof）:1
        System.out.println("索引值（binarySearch）:" + index2);//索引值（binarySearch）:2
    }
}
