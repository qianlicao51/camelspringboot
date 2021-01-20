package com.study.thinkinginjava.char17.part2;

import java.util.ArrayList;

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
        final ArrayList<StringAddress> list = new ArrayList<>();

    }
}
