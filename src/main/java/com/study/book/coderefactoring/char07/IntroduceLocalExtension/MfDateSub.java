package com.study.book.coderefactoring.char07.IntroduceLocalExtension;

import java.util.Date;

/**
 * @Author: MI
 * @Date: 2021/10/19/21:15
 * @Description:
 */
public class MfDateSub extends Date {
    public MfDateSub(String dateString) {
        super(dateString);
    }

    public MfDateSub(Date date) {
        super(date.getTime());
    }

    private static Date nextDay(Date arg) {
        return new Date(arg.getYear(), arg.getMonth(), arg.getDate() + 1);
    }
}
