package com.study.thinkinginjava.char17.part2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/20 23:32
 */
class StringAddress {
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "StringAddress{" +
                "s='" + s + '\'' +
                '}';
    }
}

public class FillingLists {
    public static void main(String[] args) {
        final ArrayList<StringAddress> list = new ArrayList<>(
                Collections.nCopies(4, new StringAddress("hello")));
        System.out.println(list);

        //flll只能替换List中的元素，而不能添加新的元素
        Collections.fill(list, new StringAddress("world!"));
        System.out.println(list);
    }
}
