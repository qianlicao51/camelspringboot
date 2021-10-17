package com.study.book.coderefactoring.char06.SplitTemporaryVariable;

/**
 * @Author: MI
 * @Date: 2021/10/16/21:47
 * @Description:
 */
public class SplitTemporaryVariable {
    double _height, _width;

    void original() {
        double temp = 2 * (_height + _width);
        System.out.println(temp);
        temp = _height * _width;
        System.out.println(temp);
    }

    void newMethod() {
        final double perimeter = 2 * (_height + _width);
        System.out.println(perimeter);
        final double area = _height * _width;
        System.out.println(area);
    }


}
