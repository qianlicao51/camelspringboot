package com.study.book.sf;

/**
 * @author MI
 * @ClassName q03.java
 * @createTime 2021年09月28日 15:32:00
 */
public class q03 {
    public static void main(String[] args) {
        boolean[] arr = new boolean[100]; // 表示100张牌，默认是false（背面朝上）
        // start表示每一轮翻的第一张牌的下标; skip表示每一轮隔几张牌翻一次
        for (int start = 1; start < arr.length; start++) {
            int i = start;
            while (i < arr.length) {
                arr[i] = !arr[i]; // 翻牌
                i += start + 1; // 注意：相隔s张牌，两张牌的下标相差(s+1)。比如1和4，相隔2张牌，下标相差3
            }
            System.out.println("skip = " + start); // 与start的值相同，该变量可以用start代替
            print(arr); // 自定义的方法print(xx)，便于查看每一轮翻牌后的状态
        }
        // 统计最终结果，打印为false的牌的数字
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i]) System.out.print(i + 1 + " ");
        }
    }

    // 每十个换一行，输出当前数组的内容
    private static void print(boolean[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print((arr[i] ? 1 : 0) + " "); // 1 true;  0 false
            if ((i + 1) % 10 == 0) System.out.println();
        }
        System.out.println("-------------------");
    }
}
