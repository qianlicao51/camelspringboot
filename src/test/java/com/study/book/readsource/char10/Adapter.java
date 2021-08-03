package com.study.book.readsource.char10;

public class Adapter extends Adaptee implements Target {
    @Override
    public void sayHi() {
        super.sayHello();
    }
}
