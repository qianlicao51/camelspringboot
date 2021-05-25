package com.study.suggest151.char06;

import java.util.Arrays;
import java.util.List;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/25 15:13
 */
public class Client084 {
    public static void main(String[] args) {
        //summer 是小写
        final List<String> params = Arrays.asList("Spring", "summer");
        for (String name : params) {
            final Season season = Season.valueOf(name);
            if (season != null) {
                System.out.println(season);
            } else {
                System.out.println("没有该枚举项");
            }
        }
    }

    static enum Season {
        Spring("春"), Summer("夏"), Autumn("秋"), Winter("冬");
        private String desc;

        Season(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
