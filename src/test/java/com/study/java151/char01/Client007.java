package com.study.java151.char01;

/**
 * @author MI
 * @version 1.0
 * @date 2021/5/23 22:45
 */
public class Client007 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            count = count++;
        }
        //结果为0
        System.out.println(count);
    }
}
