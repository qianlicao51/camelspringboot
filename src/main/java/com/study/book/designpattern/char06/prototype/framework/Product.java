package com.study.book.designpattern.char06.prototype.framework;
import java.lang.Cloneable;

public interface Product extends Cloneable {
    void use(String s);
    Product createClone();
}
