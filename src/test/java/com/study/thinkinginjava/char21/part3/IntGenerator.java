package com.study.thinkinginjava.char21.part3;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/14 22:13
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public void cancel() {
        canceled = true;
    }

    public boolean isCanceled() {
        return canceled;
    }
}
