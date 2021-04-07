package com.study.offer.base.char05;

import java.util.Arrays;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/7 13:49
 */
public class binarySearch {
    /**
     * 二分查找算法
     */
    public static int binarySearch(int[] array, int a) {
        int low = 0;
        int high = array.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a == array[mid]) {
                return mid;
            } else if (a > array[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序法
     */
    public static int[] bubblSort(int arr[]) {
        //外层循环控制排序趟数
        for (int i = 0; i < arr.length - 1; i++) {
            //内存循环控制每一趟排序多少次
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr;
    }

    public static int[] insertSort(int array[]) {
        for (int index = 1; index < array.length; index++) {
            int temp = array[index];
            int leftindex = index - 1;
            while (leftindex >= 0 && array[leftindex] > temp) {
                // while (leftindex >= 0 && array[leftindex] > temp) {
                array[leftindex + 1] = array[leftindex];
                leftindex--;
            }
            array[leftindex + 1] = temp;
        }
        return array;
    }

    public static int[] quickSort(int[] arr, int low, int high) {
        int start = low;//从前向后比较索引
        int end = high;//从后向前比较索引
        int key = arr[low];//基准值
        while (end > start) {
            //从后向前比较
            while (end > start && arr[end] >= key) {
                end--;
                //如果没有比基准值小的，则比较下一个，直到比基准值小的，则交换位置
                //然后又从前向后 比较
                if (arr[end] <= key) {
                    int temp = arr[end];
                    arr[end] = arr[start];
                    arr[start] = temp;
                }
            }
            //从前向后比较
            while (end > start && arr[start] <= key) {
                start++;
                if (arr[start] >= key) {
                    int temp = arr[start];
                    arr[start] = arr[end];
                    arr[end] = temp;
                }
            }
        }
        return arr;
    }

    /*
     * 插入排序方法|https://blog.csdn.net/qq_42857603/article/details/81605124
     */
    public static int[] doInsertSort(int[] array) {
        for (int index = 1; index < array.length; index++) {//外层向右的index，即作为比较对象的数据的index
            int temp = array[index];//用作比较的数据
            int leftindex = index - 1;
            while (leftindex >= 0 && array[leftindex] > temp) {//当比到最左边或者遇到比temp小的数据时，结束循环
                array[leftindex + 1] = array[leftindex];
                leftindex--;
            }
            array[leftindex + 1] = temp;//把temp放到空位上
        }
        return array;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{11, 14, 7, 9, 12};
        // System.out.println(binarySearch(arr, 7));
        System.out.println(Arrays.toString(insertSort(arr)));

    }


}
