package com.study.wwj.api.char02;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/17 15:58
 */
public class AromicReferenceExample3 {
    //定义 AtomicReference ，初始化为 0
    private static AtomicReference<DebitCard> debitCardRef =
            new AtomicReference<>(new DebitCard("Alex", 0));

    static void m() {
        final DebitCard preDc = debitCardRef.get();
        final DebitCard result = debitCardRef.getAndUpdate(dc -> new DebitCard(dc.getAccount(), dc.getAmount() + 10));
        assert preDc == result;
        assert result != debitCardRef.get();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    // 获取 AtomicReference 的当前值
                    final DebitCard dc = debitCardRef.get();
                    final DebitCard newDc = new DebitCard(dc.getAccount(), dc.getAmount() + 10);
                    //基于 CAS 算法更新 AtomicReference 的当前值
                    if (debitCardRef.compareAndSet(dc, newDc)) {
                        System.out.println(newDc);
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

