package com.study.book.designpattern.uml;

/**
 * @author MI
 * @ClassName Printable.java
 * @createTime 2021年08月14日 17:52:00
 */
public interface Printable {
    abstract void print();
    abstract void newPage();
}
class PrintClass implements Printable{
    @Override
    public void print() {
    }
    @Override
    public void newPage() {
    }
}
