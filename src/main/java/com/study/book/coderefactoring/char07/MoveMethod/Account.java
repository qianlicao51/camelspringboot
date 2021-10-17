package com.study.book.coderefactoring.char07.MoveMethod;

/**
 * @Author: MI
 * @Date: 2021/10/17/16:54
 * @Description:
 */
public class Account {
    private AccountType _type;
    private int _daysOverdrawn;

    double bankCharge() {
        double result = 4.5;
        if (_daysOverdrawn > 0) {
            result += _type.overdraftChange(this);
        }
        return result;
    }

    double overdraftChange() {
        return _type.overdraftChange(this);
    }

    public int get_daysOverdrawn() {
        return _daysOverdrawn;
    }
}
