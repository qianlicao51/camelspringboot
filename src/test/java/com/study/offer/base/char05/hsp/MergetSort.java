package com.study.offer.base.char05.hsp;

/**
 * @author study
 * @version 1.0
 * @date 2021/4/7 17:25
 */
public class MergetSort {

    // 分+合
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
        }
    }

    /**
     * 合并数组
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge(int[] arr, int left, int mid,
                             int right, int[] temp) {
        int i = left;    // 初始化i,左边有序序列的初始索引
        int j = mid + 1;
        int t = 0;//指向temp数组的当前索引
        // (一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i < mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        // (二)
        // 把剩余的数据的一边数据依次全部填充到temp
        while (i <= mid) {
            //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j < right) {
            temp[t] = arr[i];
            t += 1;
            j += 1;
        }

        // (三)
        // 将temp 数组的元素拷贝到arr
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
        }

    }

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};

    }
}
