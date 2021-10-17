package com.study.book.coderefactoring.char06.ExtractMethod;

/**
 * @Author: MI
 * @Date: 2021/10/16/15:16
 * @Description:
 */
public class Demo01 {

    static class Original {
        private String _name = "";

        void printOwing(double amount) {
            printBanner();
            //print details
            System.out.println("name:" + _name);
            System.out.println("amount:" + amount);
        }

        public void printBanner() {
        }
    }

    static class newClass {
        private String _name = "";

        void printOwing(double amount) {
            printBanner();
            //print details
            printDetails(amount);
        }

        private void printDetails(double amount) {
            System.out.println("name:" + _name);
            System.out.println("amount:" + amount);
        }

        public void printBanner() {
        }
    }
}
