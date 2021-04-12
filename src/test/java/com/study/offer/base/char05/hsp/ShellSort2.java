package com.study.offer.base.char05.hsp;

import java.util.Arrays;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/7 18:22
 */
public class ShellSort2 {
    //希尔排序
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    //希尔排序-移位
    public static void shellSort2(int[] arr) {
        //使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从 gap个元素，逐个对其所在的元素进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出 while之后，就该temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }
    }
}
