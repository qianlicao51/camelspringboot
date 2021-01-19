package com.study.thinkinginjava.char21.part4;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/19 21:33
 */
public class MultiLock {

    /**
     * 这个例子说明：同一个赋值如何被同一个任务多次获得
     * <p>
     * 一个任务应该能够调用任何在同一个对象中的其他synchronized 方法。
     */
    public static void main(String[] args) {
        final MultiLock multiLock = new MultiLock();
        new Thread(() -> {
            multiLock.f1(10);
        }).start();

    }

    public synchronized void f1(int count) {
        if (count-- > 0) {
            System.out.println("f1() calling f2() with count " + count);
            f2(count);
        }
    }

    public synchronized void f2(int count) {
        if (count-- > 0) {
            System.out.println("f2() calling f1() with count " + count);
            f1(count);
        }
    }

}
