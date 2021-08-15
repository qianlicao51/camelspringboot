package com.study.book.designpattern.uml;

/**
 * @author MI
 * @ClassName ParentClass.java
 * @createTime 2021年08月14日 16:14:00
 */
abstract class ParentClass {
    int field1;
    static char field2;
    abstract void methodA();
    double methodB(){
        return 0.0;
    }
}
class ChildClass extends ParentClass{
    @Override
    void methodA() {
    }
    static void methodC(){
    }
}

