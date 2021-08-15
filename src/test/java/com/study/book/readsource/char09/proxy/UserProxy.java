package com.study.book.readsource.char09.proxy;

/**
 * @author MI
 * @ClassName UserProxy.java
 * @Description TODO 静态代理
 * @createTime 2021年08月05日 10:21:00
 */
public class UserProxy implements UserInterface{
    private UserInterface target;
    public UserProxy(UserInterface target) {
        this.target = target;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("pre words");
        target.sayHello(name);
        System.out.println("post words");
        return name;
    }
}
