package com.study.jvm.char07;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/19 15:17
 */
public class ConstClass {
    static {
        System.out.println("ConstClass.static 初始值设定项");
    }

    public static final String HELLOWORLD = "hello world";
}
