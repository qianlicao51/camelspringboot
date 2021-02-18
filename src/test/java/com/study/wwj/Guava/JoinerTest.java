package com.study.wwj.Guava;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/16 10:50
 */
public class JoinerTest {

    private final List<String> stringList = Arrays.asList(
            "Google", "Guava", "Java", "Scala", "Kafka"
    );

    private final List<String> stringListWithNullValue = Arrays.asList(
            "Google", "Guava", "Java", "Scala", null
    );

    @Test
    public void testJoinOnJoin() {
        final String join = Joiner.on("#").join(stringList);
        System.out.println(join);//Google#Guava#Java#Scala#Kafka
    }

    @Test
    public void testJoinOnJoinNull() {
        //NullPointerException
        final String join = Joiner.on("#").join(stringListWithNullValue);
        System.out.println(join);
    }

    @Test
    public void testJoinOnJoinNullNullButSkip() {
        //Google#Guava#Java#Scala
        System.out.println(Joiner.on("#").skipNulls().join(stringListWithNullValue));
    }

    @Test
    public void testJoinOnJoinNullNullButDefaultValue() {
        //Google#Guava#Java#Scala#Default
        System.out.println(Joiner.on("#").useForNull("Default").join(stringListWithNullValue));
    }

    @Test
    void testJoin_on_Append_to_StringBuilder() {
        final StringBuilder builder = new StringBuilder();
        final StringBuilder stringBuilder = Joiner.on("#").appendTo(builder, stringList);
        System.out.println(builder.hashCode() == stringBuilder.hashCode());
        System.out.println(stringBuilder);
    }
}
