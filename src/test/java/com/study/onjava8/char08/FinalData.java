package com.study.onjava8.char08;

import java.util.Random;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/14 21:58
 */
public class FinalData {
    //https://lingcoder.github.io/OnJava8/#/book/08-Reuse?id=final%e5%85%b3%e9%94%ae%e5%ad%97
    private static Random rand = new Random(47);
    private String id;

    public FinalData(String id) {
        this.id = id;
    }

    //Typical public constant
    public static final int VALUE_THREE = 39;
    static final int INT_5 = rand.nextInt(20);
    private static final int VALUE_TWO = 99;
    private static final Value VAL_3 = new Value(33);
    //can be compile-time constants|编译时常量
    private final int valueOne = 9;
    // Cannot be compile-time constants:
    private final int i4 = rand.nextInt(20);
    private final Value v2 = new Value(22);
    //Arrays
    private final int[] a = {1, 2, 3, 4};
    private Value v1 = new Value(1);

    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return id + ": " + "i4 = " + i4 + ", INT_5 = " + INT_5;
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
