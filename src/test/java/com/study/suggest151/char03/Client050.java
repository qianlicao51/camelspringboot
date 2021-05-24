package com.study.suggest151.char03;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 17:02
 */
public class Client050 {
    public static void main(String[] args) {
        System.out.println("Client050.main");
        System.out.println(PkgConst.PACKAGE_CONST);
        new PkgClass().test();
    }
}

class PkgClass {
    void test() {
        System.out.println("PkgClass.test");
    }
}

class PkgConst {
    static final String PACKAGE_CONST = "ABC";
}
