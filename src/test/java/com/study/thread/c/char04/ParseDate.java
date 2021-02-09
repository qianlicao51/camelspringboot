package com.study.thread.c.char04;

import org.joda.time.DateTime;

/**
 * @author MI
 * @version 1.0
 * @date 2021/2/9 22:10
 */
public class ParseDate implements Runnable {
    public static final String FORMAT_Date = "yyyy-MM-dd HH:mm:ss:SSS";

    int i = 0;

    public ParseDate(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(i + ":" + new DateTime().toString(FORMAT_Date));
    }
}
