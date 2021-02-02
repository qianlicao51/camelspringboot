package com.study.java82.char01;

import java.io.File;
import java.io.FileFilter;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/30 12:12
 */
public class Demo01 {
    public static void main(String[] args) {

        //筛选隐藏文件
        final File[] files = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });
        //筛选隐藏文件 java8写法
        new File(".").listFiles(File::isHidden);
    }
}
