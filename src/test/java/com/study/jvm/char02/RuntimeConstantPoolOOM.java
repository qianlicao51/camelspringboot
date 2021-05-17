package com.study.jvm.char02;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/17 17:34
 * VM Args -XX:PermSize=  最小尺寸，初始分配
 * -XX:MaxPermSize=  最大允许分配尺寸，按需分配
 * -XX:PermSize=6M -XX:MaxPermSize=6M
 */
public class RuntimeConstantPoolOOM {
    @Test
    void test() {
        //使用Set保持着常量池引用，避免Full GC回收
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }

    }
}
