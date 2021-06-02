package com.study.gym.booka.char03;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author MI
 * @version 1.0
 * @date 2021/6/2 21:20
 */
public class MyObject {
    public static void main(String[] args) {
        //构造对这个对象的软引用
        //强引用
        MyObject obj = new MyObject();
        //创建引用队列
        final ReferenceQueue<MyObject> softQueue = new ReferenceQueue<>();

        //创建软引用
        final SoftReference<MyObject> softRef = new SoftReference<>(obj, softQueue);

    }

    //软引用的使用
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        //被回收时输出
        System.out.println("MyObject.finalize called");
    }

    @Override
    public String toString() {
        return "I am MyObject";
    }
}
