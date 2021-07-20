package com.study.book.optjava.jmh;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author study
 * @version 1.0
 * @date 2021/7/16 10:41
 */
public class ClassicSort {
    // -Xms2048m -Xmx2048m -XX:+PrintCompilation ClassicSort
    private static final int N = 1_000;
    private static final int I = 150_000;
    private static final List<Integer> testData = new ArrayList<>();

    @Test
    public void test() {
        Random randomGenerator = new Random();
        for (int i = 0; i < N; i++) {
            testData.add(randomGenerator.nextInt(Integer.MAX_VALUE));
        }
        System.out.println("Testing Sort Algorithm");

        double startTime = System.nanoTime();
        for (int i = 0; i < I; i++) {
            List<Integer> copy = new ArrayList<Integer>(testData);
            Collections.sort(copy);
        }

        double endTime = System.nanoTime();
        double timePerOperation = ((endTime - startTime) / (1_000_000_000L * I));
        System.out.println("Result: " + (1 / timePerOperation) + " op/s");
    }
}
