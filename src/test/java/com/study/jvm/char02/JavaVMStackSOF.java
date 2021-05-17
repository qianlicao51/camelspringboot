package com.study.jvm.char02;

import org.junit.jupiter.api.Test;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/17 16:40
 * VM Args -Xss128k
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    @Test
    void test() {
        final JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack lentgh:" + oom.stackLength);
            e.printStackTrace();
        }
    }
}
