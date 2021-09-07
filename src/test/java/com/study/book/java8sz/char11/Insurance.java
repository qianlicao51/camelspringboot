package com.study.book.java8sz.char11;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author MI
 * @ClassName Insurance.java
 * @createTime 2021年09月07日 11:02:00
 */
public class Insurance {
    private String name;

    public String getName() {
        return name;
    }

Optional<Integer> stringToInt(String numSer) {
    try {
        return Optional.of(Integer.parseInt(numSer));
    } catch (NumberFormatException e) {
        return Optional.empty();
    }
}

    @Test
    void test() {
        Optional<Integer> a = stringToInt("a");
        System.out.println(a.isPresent());
    }
}
