package com.study.onjava8.char06;

/**
 * @author study
 * @version 1.0
 * @date 2021/1/13 9:54
 */
class Person {
    public void eat(Apple apple) {
        Apple peeled = apple.getPeeled();
        System.out.println("Yummy");
    }
}

class Peeler {
    static Apple peel(Apple apple) {
        // ... remove peel
        return apple; // Peeled
    }
}

class Apple {
    /**
     * 1 this 关键字在向其他方法传递当前对象
     * 2 this 的另一个用法。参数列表中的变量名 s 和成员变量名 s 相同，会引起混淆。
     * 你可以通过 this.s 表明你指的是成员变量 s，从而避免重复。
     */
    Apple getPeeled() {

        return Peeler.peel(this);
    }
}

public class PassingThis {
    public static void main(String[] args) {
        new Person().eat(new Apple());
        //未初始化的局部变量更有可能是程序员的疏忽
        int i = 0;
        i++;

    }
}
