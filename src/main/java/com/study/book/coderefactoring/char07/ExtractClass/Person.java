package com.study.book.coderefactoring.char07.ExtractClass;

/**
 * @Author: MI
 * @Date: 2021/10/17/20:59
 * @Description:
 */
public class Person {

    private TelephoneNumber _officeTelepone = new TelephoneNumber();
    private String _name;

    public String get_name() {
        return _name;
    }

    public String getTelephoneNumber() {
        return _officeTelepone.getTelephoneNumber();
    }

    TelephoneNumber get_officeTelepone() {
        return _officeTelepone;
    }
}
