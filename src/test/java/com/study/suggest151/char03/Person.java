package com.study.suggest151.char03;

import lombok.Getter;
import lombok.Setter;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 16:03
 */
@Setter
@Getter
public class Person implements Cloneable {
    private String name;
    private Person father;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Person father) {
        this.name = name;
        this.father = father;
    }

    public Person clone() {
        Person p = null;
        try {
            p = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
