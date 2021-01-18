package com.study.thinkinginjava.char21.part3;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 22:24
 */
public class EventGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    public static void main(String[] args) {
        EventChecker.test(new EventGenerator());
    }

    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }
}
