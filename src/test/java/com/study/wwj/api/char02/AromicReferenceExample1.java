package com.study.wwj.api.char02;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/17 15:58
 */
public class AromicReferenceExample1 {
    // volatile 关键字修饰，每次对 DebitCard 对象的写入操作都会被其他线程看到
    static volatile DebitCard debitCard = new DebitCard("Alex", 0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (AromicReferenceExample1.class) {
                        //读取全局 DebitCard 对象的引用
                        final DebitCard dc = debitCard;
                        //基于全局的DebitCard 的金额增加10元 并且产生一个新的DebitCard
                        final DebitCard newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                        System.out.println(newDc);
                        //修改全局的 对象引用
                        debitCard = newDc;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(20));
                    } catch (InterruptedException e) {
                    }

                }
            }, "T-" + i).start();
        }
    }
}
/**
 * 虽然被volatile关键字修饰的变量每次更改都可以立即被其他线程看到，
 * 但是我们针对对象引用的修改其实至少包含了两个步骤：获取该引用和改变该引用
 * (每一个步骤都是原子性的操作，但组合起来就无法保证原子性了)
 */

