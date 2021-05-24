package com.study.suggest151.char02;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 14:32
 */
public class Clinet025 {
    public static void main(String[] args) {
        //存款
        BigDecimal d = new BigDecimal("888888");
        //月利率 乘3计算季利率
        BigDecimal r = new BigDecimal(0.001875 * 3);
        //银行家舍入法
        final BigDecimal i = d.multiply(r).setScale(2, RoundingMode.HALF_EVEN);
    }

    public static void main1(String[] args) {
        //11
        System.out.println("10.5近视值" + Math.round(10.5));
        //-10
        System.out.println("-10.5近视值" + Math.round(-10.5));
    }
}
