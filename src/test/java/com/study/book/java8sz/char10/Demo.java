package com.study.book.java8sz.char10;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author MI
 * @ClassName Demo.java
 * @createTime 2021年09月08日 14:02:00
 */
public class Demo {
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {
        private Car car;

        public Car getCar() {
            return car;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Car {
        private Insurance insurance;

        public Insurance getInsurance() {
            return insurance;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }

    public String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }

    /**
     * optional map
     */
    @Test
    public void map() {
        Insurance insurance = new Insurance();
        Optional<Insurance> optInsurance = Optional.of(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);
    }

    @Test
    public <U> void flapMap() {
        Insurance insurance = new Insurance("平安车险");
        Car car = new Car(insurance);
        Person person = new Person(car);
        Optional<Person> optPerson = Optional.ofNullable(person);
        Optional<String> name = optPerson.map(Person::getCar)
                .map(Car::getInsurance)
                .map(Insurance::getName);
        System.out.println(name.get());
    }

    @Test
    public void flapMaps() {
        Insurance insurance = new Insurance("平安车险");
        Car car = new Car(insurance);
        Person person = new Person(car);

    }
}
