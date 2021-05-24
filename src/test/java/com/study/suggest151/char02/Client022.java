package com.study.suggest151.char02;

import java.util.Scanner;

/**
 * @author study
 * @version 1.0
 * @date 2021/5/24 14:17
 */
public class Client022 {
    //一个会员拥有产品的最多数量
    private final static int LIMIT=2000;

    public static void main(String[] args) {
        //会员当前拥有的产品数量
        int cur=1000;
        final Scanner input = new Scanner(System.in);
        System.out.println("输入需要预定的数量:");
        while (input.hasNextInt()){
            final int order = input.nextInt();
            if (order>0&order+cur<=LIMIT){
                System.out.println("你已经成功预定的"+order+"个产品");
            }else {
                System.out.println("超过限额，预定失败");
            }
        }
    }
}
