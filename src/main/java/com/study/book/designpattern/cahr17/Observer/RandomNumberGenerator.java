package com.study.book.designpattern.cahr17.Observer;

import java.util.Random;

public class RandomNumberGenerator extends NumberGenerator {
    private Random random = new Random();   // 随机数生成器
    private int number;                     // 当前数值
    @Override
    public int getNumber() {                // 获取当前数值
        return number;
    }
    @Override
    public void execute() {
        for (int i = 0; i < 20; i++) {
            number = random.nextInt(50);
            notifyObservers();
        }
    }
}
