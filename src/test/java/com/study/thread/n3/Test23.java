package com.study.thread.n3;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/26 21:41
 */
public class Test23 {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");

        new Philospoher("苏格拉底", c1, c2).start();
        new Philospoher("柏拉图", c2, c3).start();
        new Philospoher("亚里士多德", c3, c4).start();
        new Philospoher("郝拉克里特", c4, c5).start();
        new Philospoher("阿基米德", c5, c1).start();
    }
}

/**
 * 哲学家就餐问题
 */
@Slf4j(topic = "c.Philospoher")
class Philospoher extends Thread {
    Chopstick left, right;
    String name;

    public Philospoher(String name, Chopstick left, Chopstick right) {
        this.left = left;
        this.right = right;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            if (left.tryLock()) {
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() {
        log.debug("[{}]eating....", this.name);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Chopstick extends ReentrantLock {
    String name;

    public Chopstick(String name) {
        this.name = name;
    }
}
