package com.study.suggest151.char03;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 16:18
 */
public class Client45 {
    @Setter
    @Getter
    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Person) {
                final Person p = (Person) o;
                if (p.getName() == null || name == null) {
                    return false;
                }
                return name.equalsIgnoreCase(p.getName());
            }
            return false;
        }
    }

    public static void main(String[] args) {
        final Person p1 = new Person("张三");
        //这个名字后有个空格
        final Person p2 = new Person(null);
        List<Person> l = new ArrayList<>(2);
        l.add(p1);
        l.add(p2);
        //true
        System.out.println("是否包含张三" + l.contains(p1));
        //false
        System.out.println("是否包含张三" + l.contains(p2));
    }
}
