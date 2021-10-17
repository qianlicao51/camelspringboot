package com.study.book.coderefactoring.char06.IntroduceExplainingVariable;

/**
 * @Author: MI
 * @Date: 2021/10/16/21:26
 * @Description:
 */
public class Demo01 {
    String platform;
    String browser;
    int resize;

    public void original() {
        if ((platform.toUpperCase().indexOf("MAC") > -1) &&
                (browser.toUpperCase().indexOf("IE") > -1) &&
                wasInitialized() && resize > 0
        ) {
            // do something
        }

    }

    public void newMethod() {
        final boolean isMacOSs = platform.toUpperCase().indexOf("MAC") > -1;
        final boolean isIEBrowser = browser.toUpperCase().indexOf("IE") > -1;
        final boolean wasResized = resize > 0;

        if (isMacOSs && isIEBrowser && wasInitialized() && wasResized) {
            // do something
        }
    }

    boolean wasInitialized() {
        return true;
    }

}
