package com.study.book.springsource.char02;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/23 21:37
 */
public class MyTestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void init() {
        System.out.println("MyTestBean.init");
        System.out.println(this);
        System.out.println("MyTestBean.init");
    }
}
