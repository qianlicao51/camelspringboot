package com.study.book.designpattern.char14.ChainOfResponsibility;

public class OddSupport extends Support {
    public OddSupport(String name) {                // 构造函数
        super(name);
    }
    @Override
    protected boolean resolve(Trouble trouble) {    // 解决问题的方法
        if (trouble.getNumber() % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
}
