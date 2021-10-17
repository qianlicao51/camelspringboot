package com.study.book.coderefactoring.char07.MoveMethod;

/**
 * @Author: MI
 * @Date: 2021/10/17/16:56
 * @Description:
 */
public class AccountType {
    double overdraftChange(Account account) {
        if (isPremium()) {
            double result = 10;
            if (account.get_daysOverdrawn() > 7) {
                result += (account.get_daysOverdrawn() - 7) * 0.85;
            }
            return result;
        } else {
            return account.get_daysOverdrawn() * 1.75;
        }
    }

    public boolean isPremium() {
        return true;
    }
}
