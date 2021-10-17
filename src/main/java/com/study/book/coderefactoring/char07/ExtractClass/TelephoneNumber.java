package com.study.book.coderefactoring.char07.ExtractClass;

/**
 * @Author: MI
 * @Date: 2021/10/17/21:05
 * @Description:
 */
public class TelephoneNumber {
    private String _areaCode;
    private String _number;

    public String getTelephoneNumber() {
        return ("(" + _areaCode + ")" + _number);
    }

    public String getAreaCode() {
        return _areaCode;
    }

    public void setAreaCode(String _areaCode) {
        this._areaCode = _areaCode;
    }

    public String get_number() {
        return _number;
    }

    public void set_number(String _number) {
        this._number = _number;
    }
}
