package com.study.book.sf;

/**
 * @author MI
 * @ClassName q01.java
 * @createTime 2021年09月28日 14:54:00
 */
public class q01 {
    //https://github.com/leungwensen/70-math-quizs-for-programmers/blob/master/zh_CN
    public static void main(String[] args) {
        int num = 11;
        while (true) {
            if (Integer.toBinaryString(num).equals(new StringBuilder(Integer.toBinaryString(num)).reverse().toString())
                    && Integer.toOctalString(num).equals(new StringBuilder(Integer.toOctalString(num)).reverse().toString())
                    && String.valueOf(num).equals(new StringBuilder(String.valueOf(num)).reverse().toString())
            ) {
                break;
            }
            // 检索奇数，每次加2
            num += 2;
        }
        System.out.println(num);
    }
}
