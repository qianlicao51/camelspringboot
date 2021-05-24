package com.study.suggest151.char01;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 10:41
 */
@Setter
@Getter
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    //姓名
    private String name;
    //薪水
    private transient Salary salary;

    //序列化委托方法
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(salary.getBasePay());
    }

    //反序列化时委托方法
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        salary = new Salary(in.readInt(), 0);
    }

    public Person(String name, Salary salary) {
        this.name = name;
        this.salary = salary;
    }
}
