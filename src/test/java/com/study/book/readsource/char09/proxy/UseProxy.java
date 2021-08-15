package com.study.book.readsource.char09.proxy;


import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class UseProxy {

    @Test
    public void testProxy() {
        //生成被代理对象
        User user = new User();
        //生成代理
        UserProxy userProxy = new UserProxy(user);
        //触发代理方法
        userProxy.sayHello("静态代理");
    }

    @Test
    public void test() {

    }
}
