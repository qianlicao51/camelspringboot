package com.study.book.readsource.char10;

//对象适配器类图
public class Adapter2 implements Target {
    //目标类的对象
    private Adaptee adaptee;

    //初始化适配器时可以指定目标类对象
    public Adapter2(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void sayHi() {
        adaptee.sayHello();
    }
}
