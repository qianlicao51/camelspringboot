package com.study.wwj.Guava;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/16 11:32
 */
public class FunctionExample {
    public static void main(String[] args) {
        final Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public @Nullable Integer apply(@Nullable String input) {
                Preconditions.checkNotNull(input, "输入不能为null");
                return input.length();
            }
        };

        final Integer abc = function.apply(null);
        System.out.println(abc);
    }
}
