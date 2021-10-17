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
        for (int i = 0; i < people.length; i++) {
            if (people[i].equals("Don")) {
                return "Don";
            }
            if (people[i].equals("John")) {
                return "John";
            }
            if (people[i].equals("Kent")) {
                return "Kent";
            }
        }
        return "";
    }

    String foundPersonNew(String[] people) {
        List<String> candidates = Arrays.asList(new String[]{"Don", "John", "Kent"});
        for (String person : people) {
            if (candidates.contains(person)) {
                return person;
            }
        }
        return "";
    }
}
