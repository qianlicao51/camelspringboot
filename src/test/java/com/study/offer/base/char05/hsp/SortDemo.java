package com.study.offer.base.char05.hsp;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/7 15:52
 * https://www.bilibili.com/video/BV1B4411H76f?p=66
 */
public class SortDemo {

    /**
     * 快速排序
     */
    public static void quickSort(int arr[], int left, int right) {
        int l = left;
        int r = right;//右下标
        int pivot = arr[(left + right) / 2];//中轴值
        //循环目的是让 比 pivot小的值 放到左边，比pivot大的值放到右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot的值才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边 找比 pivot 小的值。
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>r ,说明 pivot左右两边的值，已经全部按照 左边小于pivot，右边大有pivot排序了
            if (l >= r) {
                break;
            }
            //交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //交换之后 发现 arr[l]==pivot ,前移一下
            if (arr[l] == pivot) {
                r -= 1;
            }
            //l后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //上述代码第一次移动完毕。
        //如果l==r 必须加一 ，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        quickSort(arr, 0, arr.length - 1);
    }
}
