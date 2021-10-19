package com.study.book.coderefactoring.char07.IntroduceLocalExtension;

import java.util.Date;

/**
 * @Author: MI
 * @Date: 2021/10/19/21:43
 * @Description:
 */
public class MfDateWrap {
    private Date _original;

    public MfDateWrap(String dateString) {
        _original = new Date(dateString);
    }

    public MfDateWrap(Date arg) {
        _original = arg;
    }

    public int getYear() {
        return _original.getYear();
    }

}
