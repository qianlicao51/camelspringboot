package com.study.gym.booka.char05;

/**
 * @author study
 * @version 1.0
 * @date 2021/6/3 13:46
 * -XX:+PrintGCDetails -Xmx20M -Xms20M
 */
public class PutInEden {
    public static void main(String args[]) {
        byte[] b1, b2, b3, b4;//定义变量
        b1 = new byte[1024 * 1024];//分配 1 MB 堆空间，考察堆空间的情况。
        b2 = new byte[1024 * 1024];
        b3 = new byte[1024 * 1024];
        b4 = new byte[1024 * 1024];
    }
}
