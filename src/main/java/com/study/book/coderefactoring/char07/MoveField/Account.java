package com.study.book.coderefactoring.char07.MoveField;


/**
 * @Author: MI
 * @Date: 2021/10/17/20:23
 * @Description:
 */
public class Account {
    private AccountType _type;

    double interestForAmount_days(double amount, int days) {
        return getTnterestRate() * amount * days / 365;
    }

    public void set_interestRate(double arg) {
        _type.set_interestRate(arg);
    }

    public double getTnterestRate() {
        return _type.get_interestRate();
    }
}
