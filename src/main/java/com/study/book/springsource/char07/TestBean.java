package com.study.book.springsource.char07;

import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/4 16:00
 */
public class TestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test");
    }
}
