package com.study.book.coderefactoring.char06.InlintMethod;

/**
 * @Author: MI
 * @Date: 2021/10/16/17:22
 * @Description:
 */
public class Demo01 {
    int _numberOfLateDeliveries = 9;

    int getRating() {
        return moreThanFiveLateDeliveries() ? 2 : 1;
    }

    boolean moreThanFiveLateDeliveries() {
        return (_numberOfLateDeliveries > 5);
    }

    int getRatingNew() {
        return (_numberOfLateDeliveries > 5) ? 2 : 1;
    }
}

