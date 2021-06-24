package com.study.jvm.char08;

/**
 * @author study
 * @version 1.0
 * 方法静态分派演示
 * @date 2021/6/24 16:43
 */
public class StaticDispatch {
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        final StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);//hello,guy!
        sr.sayHello(woman);//hello,guy!
    }

    void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,lentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }
}
