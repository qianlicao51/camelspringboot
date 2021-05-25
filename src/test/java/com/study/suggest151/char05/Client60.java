package com.study.suggest151.char05;

import java.util.Arrays;
import java.util.List;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/24 20:14
 */
public class Client60 {
    public static int sum(int[] datas) {
        int sum = 0;
        for (int data : datas) {
            sum += data;
        }
        return sum;
    }

    public static int sum(List<Integer> datas) {
        int sum = 0;
        for (int data : datas) {
            sum += data;
        }
        return sum;
    }

    public static <T> T[] expandCapacity(T[] data, int newLen) {
        //不能是负值
        newLen = newLen < 0 ? 0 : newLen;
        return Arrays.copyOf(data, newLen);
    }
}
