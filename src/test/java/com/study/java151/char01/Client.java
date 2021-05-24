package com.study.java151.char01;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/23 22:18
 */
public class Client {
    public static void main1(String[] args) {
        final Client client = new Client();
        client.methodA("China", 0);
        client.methodA("China", "people");
        // client.methodA("China");
        // client.methodA("China", null);
    }

    public static void main(String[] args) {
        //向上转型
        final Base base = new Sub();
        base.fun(100, 50);
        //不转型
        final Sub sub = new Sub();
        // sub.fun(100,50);
    }

    public void methodA(String str, Integer... is) {
    }

    public void methodA(String str, String... strs) {
    }
}

class Base {
    void fun(int price, int... discounts) {
        System.out.println("Base.fun");
    }
}

//子类 覆写父类方法
class Sub extends Base {
    @Override
    void fun(int price, int[] discounts) {
        System.out.println("Sub.fun");
    }
}
