package com.study.book.coderefactoring.char06.ReplaceMethodWithMethodObject;

/**
 * @Author: MI
 * @Date: 2021/10/17/14:49
 * @Description:
 */
public class Gamma {
    private final Account _account;
    private int inputVal;
    private int quantity;
    private int yearToDate;
    private int importantValue1;
    private int importantValue2;
    private int importantValue3;

    public Gamma(Account _account, int inputVal, int quantity, int yearToDate) {
        this._account = _account;
        this.inputVal = inputVal;
        this.quantity = quantity;
        this.yearToDate = yearToDate;
    }

    int compute() {
        importantValue1 = (inputVal * quantity) + _account.dalta();
        importantValue2 = (inputVal * quantity) + 100;
        importantThing();
        importantValue3 = importantValue2 * 7;
        return importantValue3 - 2 * importantValue1;
    }

    private void importantThing() {
        if ((yearToDate - importantValue1) > 100) {
            importantValue2 -= 20;
        }
    }


}
