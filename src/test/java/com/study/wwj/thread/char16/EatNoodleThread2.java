package com.study.wwj.thread.char16;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 16:59
 */
public class EatNoodleThread2 extends Thread {
    private final String name;
    private final TablewarePair tablewarePair;

    public EatNoodleThread2(String name, TablewarePair tablewarePair) {
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    public static void main(String[] args) {
        final Tableware fork = new Tableware("fork");
        final Tableware knife = new Tableware("knife");
        final TablewarePair pair = new TablewarePair(fork, knife);
        new EatNoodleThread2("A", pair).start();
        new EatNoodleThread2("B", pair).start();
    }

    @Override
    public void run() {
        while (true) {
            eat();
        }
    }

    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + " take up " + tablewarePair.getLeftTool() + " (left)");
            System.out.println(name + " take up " + tablewarePair.getRightTool() + " (right)");
            System.out.println(name + " is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + " (right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + " (left)");
        }
    }
}
