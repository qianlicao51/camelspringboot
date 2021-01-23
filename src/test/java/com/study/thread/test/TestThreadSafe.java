package com.study.thread.test;

import java.util.ArrayList;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/23 17:53
 */
public class TestThreadSafe {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        final ThreadSafe safe = new ThreadSafe();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> safe.method1(LOOP_NUMBER)).start();
        }
    }
}

class ThreadSafe {
    /**
     *
     */
    // final ArrayList<String> list = new ArrayList<>();
    public final void method1(int loopNumber) {
        /**
         * list是局部变量，每个线程调用时会创建不同实例，没有共享。
         * method2 的参数是从 method1 中传递过来的，与 method1 中引用同一个对象
         */
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    public void method2(ArrayList<String> list) {
        list.add("1");
    }

    private void method3(ArrayList<String> list) {
        list.remove(0);
    }
}

class ThreadSafeSubClass extends ThreadSafe {
    /**
     * 存在线程安全，因为 多个线程共享了 list
     */
    public void method3(ArrayList<String> list) {
        new Thread(() -> list.remove(0)).start();
    }
}
