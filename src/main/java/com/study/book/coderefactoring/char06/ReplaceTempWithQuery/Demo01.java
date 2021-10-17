package com.study.book.coderefactoring.char06.ReplaceTempWithQuery;

/**
 * @Author: MI
 * @Date: 2021/10/16/20:51
 * @Description:
 */
public class Demo01 {
    double _itemPrice;
    int _quantity;

    public double calPrice() {
        double basePrice = _quantity * _itemPrice;
        if (basePrice > 1000) {
            return basePrice * 0.95;
        }
        return basePrice * 0.98;
    }

    public double calPriceNew() {
        if (basePrice() > 1000) {
            return basePrice() * 0.95;
        }
        return basePrice() * 0.98;
    }

    double basePrice() {
        return _quantity * _itemPrice;
    }

}
