package com.study.book.springsource.char05;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/4 14:54
 */
public class TestA {
    private TestB testB;

    public void a() {
        testB.b();
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}

class TestB {
    private TestC testC;

    public void b() {
        testC.c();
    }

    public TestC getTestC() {
        return testC;
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }
}

class TestC {
    private TestA testA;

    public void c() {
    }

    public TestA getTestA() {
        return testA;
    }

    public void setTestA(TestA testA) {
        this.testA = testA;
    }
}
