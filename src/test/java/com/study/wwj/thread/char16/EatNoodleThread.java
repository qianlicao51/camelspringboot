package com.study.wwj.thread.char16;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 16:41
 */
public class EatNoodleThread extends Thread {
    private final String name;
    //左手餐具
    private final Tableware leftTool;

    //右手边餐具
    private final Tableware rightTool;

    public EatNoodleThread(String name, Tableware leftTool, Tableware rightTool) {
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public static void main(String[] args) {
        final Tableware fork = new Tableware("fork");
        final Tableware knife = new Tableware("knife");
        new EatNoodleThread("A", fork, knife).start();
        new EatNoodleThread("B", fork, knife).start();
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + " take up " + leftTool + " (left)");
            synchronized (rightTool) {
                System.out.println(name + " take up " + rightTool + " (right)");
                System.out.println(name + " is eating now.");
                System.out.println(name + " put down " + rightTool + " (right)");
            }
            System.out.println(name + " put down " + leftTool + " (left)");
        }
    }
}
