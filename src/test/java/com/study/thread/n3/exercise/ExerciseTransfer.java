package com.study.thread.n3.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/24 11:27
 */
@Slf4j(topic = "c.ExerciseTransfer")
public class ExerciseTransfer {
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
        final Account a = new Account(1000);
        final Account b = new Account(1000);
        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a.transfer(b, randomInt());
            }
        }, "t1");
        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                b.transfer(a, randomInt());
            }
        }, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("total {}", (a.getMoney() + b.getMoney()));

    }

    public static int randomInt() {
        return random.nextInt(100) + 1;
    }
}

/**
 * 账户
 */
class Account {
    private static final Lock lock = new ReentrantLock();

    private int money;

    public Account(int money) {
        this.money = money;
    }

    public void transfer(Account target, int money) {
        synchronized (Account.class) {
            if (this.money >= money) {
                this.setMoney(this.getMoney() - money);
                target.setMoney(target.getMoney() + money);
            }
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
