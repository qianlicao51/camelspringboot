package com.study.clinit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/13 15:36
 */
public class A {
    private static int num;

    static {
        num = 10;
        System.out.println("A.static 初始值设定项");
    }
}
