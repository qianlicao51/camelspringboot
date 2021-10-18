package com.study.book.sf;

/**
 * @author MI
 * @ClassName s04.java
 * @createTime 2021年09月28日 16:25:00
 */
public class q04 {
    public static void main(String[] args) {
        System.out.println(split(3, 20));
    }

    public static int split(int m, int n) {
        int count = 0;
        //当前长度
        int current = 1;
        while (n > current) {
            count++;
            // current是当前长度，同顺序思维是一样的，开始只能加一次，加两次，一直到数m之前。
            System.out.println(current + ">" + count);
            current += current < m ? current : m;
        }
        return count;
    }
}
