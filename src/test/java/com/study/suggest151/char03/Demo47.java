package com.study.suggest151.char03;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/15 14:58
 */
//编写高质量代码：改善Java程序的151个建议
//  在equals中使用getClass进行类型判断
@Data
class Person {
    private String name;
    private Person father;

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Person father) {
        this.name = name;
        this.father = father;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return new EqualsBuilder().append(name, person.name).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).toHashCode();
    }*/
}

public class Demo47 {
    public static void main(String[] args) {

        //person 类的实例作为Map的key
        Map<Person, Object> map = new HashMap<>() {
            {
                put(new Person("张三"), new Object());
            }
        };

        List<Person> list = new ArrayList<>() {{
            add(new Person("张三"));
        }};

        System.out.println(list.contains(new Person("张三")));
        System.out.println(map.containsKey(new Person("张三")));
        System.out.println(new Person("张三").hashCode());
        System.out.println(new Person("张三").hashCode());
    }
}
