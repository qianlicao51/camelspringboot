package com.study.wwj.thread.char16;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 16:40
 */
public class Tableware {
    //餐具名称
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tool:" + toolName;
    }
}
