package com.study.book.coderefactoring.char06.SubstituteAlgoriithm;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: MI
 * @Date: 2021/10/17/15:26
 * @Description:
 */
public class Demo01 {
    String foundPerson(String[] people) {
        for (String person : people) {
            if ("Don".equals(person)) {
                return "Don";
            }
            if ("John".equals(person)) {
                return "John";
            }
            if ("Kent".equals(person)) {
                return "Kent";
            }
        }
        return "";
    }

    String foundPersonNew(String[] people) {
        List<String> candidates = Arrays.asList("Don", "John", "Kent");
        for (String person : people) {
            if (candidates.contains(person)) {
                return person;
            }
        }
        return "";
    }
}
