package com.study.wwj.api.char01;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/15 19:13
 */
public class ArrayListVSLinkedList {
    private final static String DATA = "dummy DATA";
    private final static int MAX_CAPCITY = 1_000_000;
    private final static int MAX_ITERATIONS = 10;

    private static void test(List<String> list) {
        for (int i = 0; i < MAX_CAPCITY; i++) {
            list.add(DATA);
        }
    }

    private static void arrayListPerfTest(int iterations) {
        for (int i = 0; i < iterations; i++) {
            final ArrayList<String> list = new ArrayList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    private static void linkedListPerfTest(int iterations) {
        for (int i = 0; i < iterations; i++) {
            final LinkedList<String> list = new LinkedList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

    public static void main(String[] args) {
        arrayListPerfTest(MAX_ITERATIONS);
        System.out.println(Strings.repeat("#", 10));
        linkedListPerfTest(MAX_ITERATIONS);
    }
}
