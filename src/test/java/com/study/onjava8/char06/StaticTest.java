package com.study.onjava8.char06;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/13 14:59
 */
public class StaticTest {
    //位置1
    static {
        i = 3;
    }

    //位置2
    static int i  ;

    //位置1 和位置2 是按顺序执行的，可以交换顺序，但是结果是2个结果。

    //如果 位置2 没有给定初始化值，按照  静态代码块中的执行



    public static void main(String[] args) {
        System.out.println(i);
    }
}
