package com.study.suggest151.char05;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 13:57
 */
public class Client081 {
    public static void main(String[] args) {
        final SortedSet<Person> set = new TreeSet<>();
        set.add(new Person(180));
        set.add(new Person(175));
        //身高最矮的人长高了
        set.first().setHeight(185);
        for (Person p : set) {
            System.out.println("身高" + p.getHeight());
        }
    }

    @Getter
    @Setter
    static class Person implements Comparable<Person> {
        private int height;

        public Person(int height) {
            this.height = height;
        }

        @Override
        public int compareTo(Person o) {
            return new CompareToBuilder().append(height, o.height)
                    .toComparison();
        }
    }
}
