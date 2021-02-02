package com.study.java82.char16;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/31 12:51
 */
public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public static Quote parse(String s) {
        final String[] split = s.split(":");
        String shopName = split[0];
        final double price = Double.parseDouble(split[1]);
        final Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
}
