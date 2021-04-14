package com.study.onjava8.char08;

import java.util.Random;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/14 21:58
 */
public class FinalData {
    // Typical public constant:
    public static final int VALUE_THREE = 39;
    // connto be compile-time constants
    public static final int VALUE_THREE = 39;
    private static final int VALUE_TWO = 99;
    //https://lingcoder.github.io/OnJava8/#/book/08-Reuse?id=final%e5%85%b3%e9%94%ae%e5%ad%97
    private static Random rand = new Random(47);
    private final int valueOne = 9;
    private String id;

    public FinalData(String id) {
        this.id = id;
    }

    /**
     *不含参的构造函数每次都使用当前时间作为种子，随机性更强
     * https://blog.csdn.net/u011240877/article/details/52971166
     */
    /*public static void main(String[] args) {
        System.out.println("Random 不含参构造方法：");
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + random.nextInt(100) + ", ");
            }

            System.out.println("");
        }

        System.out.println("");

        System.out.println("Random 含参构造方法：");
        for (int i = 0; i < 5; i++) {
            Random random = new Random(50);
            for (int j = 0; j < 8; j++) {
                System.out.print(" " + random.nextInt(100) + ", ");
            }
            System.out.println("");
        }
    }*/
}

class Value {
    int i;

    public Value(int i) {
        this.i = i;
    }
}
