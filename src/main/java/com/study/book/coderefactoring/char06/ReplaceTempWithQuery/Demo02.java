package com.study.book.coderefactoring.char06.ReplaceTempWithQuery;

/**
 * @Author: MI
 * @Date: 2021/10/16/21:11
 * @Description:
 */
public class Demo02 {
    double _itemPrice;
    int _quantity;

    double getPrice() {
        double basePrice = _itemPrice * _quantity;
        double discountFactor;
        if (basePrice > 10000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }

    //1 添加 final
    double getPriceStrp1() {
        final double basePrice = _itemPrice * _quantity;
        final double discountFactor;
        if (basePrice > 10000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }

    //2 替换临时变量
    double getPriceStrp2() {
        final double basePrice = getBasePrice(_itemPrice, _quantity);
        final double discountFactor;
        if (basePrice > 10000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return basePrice * discountFactor;
    }

    private double getBasePrice(double itemPrice, double quantity) {
        return itemPrice * quantity;
    }

    double getPriceStrp3() {
        final double discountFactor;
        if (getBasePrice(_itemPrice, _quantity) > 10000) {
            discountFactor = 0.95;
        } else {
            discountFactor = 0.98;
        }
        return getBasePrice(_itemPrice, _quantity) * discountFactor;
    }

    double getPriceStrp4() {
        final double discountFactor = getDiscountFactor();
        return getBasePrice(_itemPrice, _quantity) * discountFactor;
    }

    private double getDiscountFactor() {
        if (getBasePrice(_itemPrice, _quantity) > 10000) {
            return 0.95;
        }
        return 0.98;
    }

    double getPriceStrp5() {
        return getBasePrice(_itemPrice, _quantity) * getDiscountFactor();
    }

}
