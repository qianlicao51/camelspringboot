package com.study.jvm.char03;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/17 21:54
 */
public class ReferenceCountingGC {
    private static final int _1mb = 1024 * 1024;
    public Object instance = null;
    /**
     * 这个成员属性的唯一意义就是占点内存，以便在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1mb];

    public static void main(String[] args) {
        ReferenceCountingGC a = new ReferenceCountingGC();
        ReferenceCountingGC b = new ReferenceCountingGC();
        a.instance = b;
        b.instance = a;

        a = null;
        b = null;
        //假设在这行发出GC ,a b 是否被回收
        System.gc();

    }
}
