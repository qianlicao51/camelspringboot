package com.study.suggest151.char04;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 17:17
 */
public class Client052 {
    public static void main(String[] args) {
        String s1 = "中国";
        String s2 = "中国";
        String s3 = new String("中国");
        String s4 = s3.intern();
        boolean b1 = (s1 == s2);//true
        boolean b2 = (s1 == s3);//false
        boolean b3 = (s1 == s4);//true
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
    }
}
