package com.study.book.readsource.char10.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class UseProxy {
    @Test
public void test() {
    // 创建被代理对象
    User user = new User();
    // 初始化一个ProxyHandler对象
    ProxyHandler proxyHandler = new ProxyHandler(user);

    // 使用Proxy类的一个静态方法生成代理对象userProxy
    UserInterface userProxy =
            (UserInterface) Proxy.newProxyInstance(
                    User.class.getClassLoader(),
                    new Class[]{UserInterface.class},
                    proxyHandler);

    // 通过接口调用相应的方法，实际由Proxy执行
    userProxy.sayHello("动态代理");
}
}
