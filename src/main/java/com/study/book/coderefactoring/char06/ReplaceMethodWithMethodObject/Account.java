package com.study.book.coderefactoring.char06.ReplaceMethodWithMethodObject;

/**
 * @Author: MI
 * @Date: 2021/10/17/14:42
 * @Description:
 */
public class Account {
    int gamaOld(int inputVal, int quantity, int yearToDate) {
        int importantValue1 = (inputVal * quantity) + dalta();
        int importantValue2 = (inputVal * quantity) + 100;
        if ((yearToDate - importantValue1) > 100) {
            importantValue2 -= 20;
        }
        int importantValue3 = importantValue2 * 7;
        return importantValue3 - 2 * importantValue1;
    }

    public int dalta() {
        return 0;
    }

    int gamma(int inputVal, int quantity, int yearToDate) {
        return new Gamma(this, inputVal, quantity, yearToDate).compute();
    }

}
