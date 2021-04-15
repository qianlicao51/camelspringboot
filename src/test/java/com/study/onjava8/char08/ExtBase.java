package com.study.onjava8.char08;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/15 11:09
 */
public class ExtBase extends Base {
    public static void main(String[] args) {
        final ExtBase extBase = new ExtBase();
        extBase.skip();
    }

    @Override
    public void pubMethod() {
        super.pubMethod();
    }
}
