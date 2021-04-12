package com.study;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/4 18:38
 */
public class Demo {
    private static int x = 10;

    static {
        //汪文君-高并发编程详解-9.3.3 类的初始化阶段
        // 静态语句块只能对后面的变量进行赋值，但是不能对其进行访问
        // System.out.println(x);
        x = 100;
    }

    public static void main(String[] args) {
        //10
        System.out.println(x);
        final List<String> list = Arrays.asList("a", "v");
        final Set<List<String>> lists = Collections.singleton(list);

    }
}
