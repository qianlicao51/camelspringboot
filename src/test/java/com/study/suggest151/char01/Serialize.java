package com.study.suggest151.char01;

import com.study.suggest151.SerializationUtils;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 10:49
 */
public class Serialize {
    public static void main(String[] args) {
        final Salary salary = new Salary(1000, 2500);
        final Person person = new Person("张三", salary);
        //HR系统持久化，并传递到计税系统
        SerializationUtils.writeObject(person);

    }
}
