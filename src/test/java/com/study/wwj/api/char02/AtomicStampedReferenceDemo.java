package com.study.wwj.api.char02;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/17 17:31
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        AtomicStampedReference<String> reference = new AtomicStampedReference<>("hello", 1);
        //失败
        assert reference.compareAndSet("hello", "world", 2, 3);
        assert reference.compareAndSet("hello", "world", 1, 2);
    }
}
