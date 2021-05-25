package com.study.suggest151.char05;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/24 22:42
 */
@Getter
@Setter
public class City implements Comparable<City> {
    private String code;
    private String name;

    public City(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static void main(String[] args) {
        final List<City> cities = new ArrayList<>();
        cities.add(new City("021", "上海"));
        cities.add(new City("021", "沪"));
        //排序
        Collections.sort(cities);
        final City city = new City("021", "沪");
        final int indexOf = cities.indexOf(city);
        final int binarySearch = Collections.binarySearch(cities, city);
        System.out.println("索引值（indexOf）:" + indexOf);
        System.out.println("索引值（binarySearch）:" + binarySearch);
    }

    @Override
    public int compareTo(City o) {
        return new CompareToBuilder().append(name, o.name).toComparison();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return new EqualsBuilder().append(code, city.code).isEquals();
    }
}
