package com.study.book.coderefactoring.char06.ExtractMethod;

import lombok.Data;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Author: MI
 * @Date: 2021/10/16/16:00
 * @Description: 范例：无局部变量
 */
public class Demo01NoVariable {
    static class Original {
        Vector<Order> _orders = new Vector<>();
        String _name = "tom";

        void printOwing() {
            Enumeration e = _orders.elements();
            double outStanding = 0.0;
            //print banner
            System.out.println("*****************");
            System.out.println("**Customer Owes**");
            System.out.println("*****************");

            //calculate outstanding
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outStanding += each.getAmount();
            }

            //print details
            System.out.println("name:" + _name);
            System.out.println("amount:" + outStanding);
        }
    }

    static class newClass extends Original {
        @Override
        void printOwing() {
            Enumeration e = _orders.elements();
            double outStanding = 0.0;
            //print banner
            printBanner();
            //calculate outstanding
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outStanding += each.getAmount();
            }
            //print details
            System.out.println("name:" + _name);
            System.out.println("amount:" + outStanding);
        }

        void printBanner() {
            System.out.println("*****************");
            System.out.println("**Customer Owes**");
            System.out.println("*****************");
        }
    }

    @Data
    class Order {
        String name;
        double amount;
    }
}
