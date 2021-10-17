package com.study.book.coderefactoring.char06.inlineTemp;

/**
 * @Author: MI
 * @Date: 2021/10/16/20:16
 * @Description:
 */
public class Demo01 {
    public boolean tst() {
        double basePrice = this.basePrice();
        return (basePrice > 1000);

    }

    public double basePrice() {
        return 1.0;
    }
}
