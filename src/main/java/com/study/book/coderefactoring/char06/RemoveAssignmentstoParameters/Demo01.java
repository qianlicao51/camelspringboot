package com.study.book.coderefactoring.char06.RemoveAssignmentstoParameters;

import lombok.Data;

/**
 * @Author: MI
 * @Date: 2021/10/16/22:03
 * @Description:
 */
public class Demo01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(18);
        person.setName("tom");
        System.out.println(person);
        modify(person);
        System.out.println(person);
    }

    static void modify(Person p) {
        Person newP = p;
        newP.setAge(19);
    }

    int discount(int inputVal, int quantity, int yearToDate) {
        if (inputVal > 50) {
            inputVal -= 2;
        }
        return inputVal;
    }

    int discount2(int inputVal, int quantity, int yearToDate) {
        int result = inputVal;
        if (inputVal > 50) {
            result -= 2;
        }
        return result;
    }

    @Data
    static class Person {
        int age;
        String name;
    }
}
