package com.study.book.gym.char06;

import java.util.concurrent.locks.StampedLock;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/19 10:16
 * StampedLock使用示例
 */
public class Point {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) {
        final long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaX;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    double distanceFromOrigin() {
        final long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!sl.validate(stamp)) {
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
