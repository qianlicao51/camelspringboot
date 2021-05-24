package com.study.suggest151.char01;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 10:39
 */
@Getter
@Setter
public class Salary implements Serializable {
    private static final long serialVersionUID = 1L;
    //基本工资
    private int basePay;
    //绩效工资
    private int bonus;

    public Salary(int basePay, int bonus) {
        this.basePay = basePay;
        this.bonus = bonus;
    }
}
