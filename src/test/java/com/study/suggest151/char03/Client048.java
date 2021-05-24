package com.study.suggest151.char03;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 16:44
 */
public class Client048 {
    @Setter
    @Getter
    private static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return new EqualsBuilder().append(name, person.name).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(name).toHashCode();
        }
    }

    public static void main(String[] args) {
        Map<Person, Object> map = new HashMap<Person, Object>() {
            {
                put(new Person("张三"), new Object());
            }
        };
        final ArrayList<Person> list = new ArrayList<>() {
            {
                add(new Person("张三"));
            }
        };
        //列表是否包含
        final boolean b1 = list.contains(new Person("张三"));
        final boolean b2 = map.containsKey(new Person("张三"));
    }
}
