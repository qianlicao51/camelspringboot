package com.study;

import java.util.HashMap;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/29 16:03
 */
public class ThreadLocalTest {
    ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "hello");
    ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
    HashMap<String, String> hashMap = new HashMap<>();
}
