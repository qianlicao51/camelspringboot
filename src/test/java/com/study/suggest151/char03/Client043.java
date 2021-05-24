package com.study.suggest151.char03;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 16:00
 */
public class Client043 {
    public static void main(String[] args) {
        final Person f = new Person("父亲");
        final Person s1 = new Person("大儿子", f);
        //小儿子的信息是通过大儿子拷贝过来的
        final Person s2 = s1.clone();
        s2.setName("小儿子");

        s1.getFather().setName("干爹");

        System.out.println(s1.getName() + " 的父亲是 " + s1.getFather().getName());
        System.out.println(s2.getName() + " 的父亲是 " + s2.getFather().getName());
    }

    public static void main1(String[] args) {
        final Person f = new Person("父亲");
        final Person s1 = new Person("大儿子", f);
        //小儿子的信息是通过大儿子拷贝过来的
        final Person s2 = s1.clone();
        s2.setName("小儿子");

        System.out.println(s1.getName() + " 的父亲是 " + s1.getFather().getName());
        System.out.println(s2.getName() + " 的父亲是 " + s2.getFather().getName());
    }
}

