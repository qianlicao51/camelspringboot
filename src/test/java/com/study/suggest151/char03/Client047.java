package com.study.suggest151.char03;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 16:30
 */
public class Client047 {
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

    @Setter
    @Getter
    private static class Employee extends Person {
        private int id;

        public Employee(String name, int id) {
            super(name);
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return new EqualsBuilder().appendSuper(super.equals(o)).append(id, employee.id).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(id).toHashCode();
        }
    }

    public static void main(String[] args) {
        final Employee e1 = new Employee("张三", 100);
        final Employee e2 = new Employee("张三", 1001);
        final Person p1 = new Person("张三");
        System.out.println(p1.equals(e1));//true
        System.out.println(p1.equals(e2));//true
        System.out.println(e1.equals(e2));//false
    }
}
