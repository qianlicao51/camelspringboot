package com.study.jvm.char07;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/19 14:42
 */
public class NotInitialization {
    public static void main(String[] args) {
        //System.out.println(SubClass.value);
        // SuperClass[] sca=new SuperClass[10];
        //非主动说那个类字典演示
        System.out.println(ConstClass.HELLOWORLD);
    }
}
