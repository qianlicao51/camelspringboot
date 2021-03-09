package com.study.offer.base.char03;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 23:02
 */
public class MyData {
    //step 1 将数据抽象成Mydata,并将数据的操作(add dec)作为类的方法
    private int j = 0;

    public static void main(String[] args) {
        final MyData myData = new MyData();
        final AddRunnable addRunnable = new AddRunnable(myData);
        final DecRunnable decRunnable = new DecRunnable(myData);
        for (int i = 0; i < 2; i++) {
            new Thread(addRunnable).start();
            new Thread(decRunnable).start();
        }
    }

    public synchronized void add() {
        j++;
        System.out.println("线程" + Thread.currentThread().getName() + " j为:" + j);
    }

    public synchronized void dec() {
        j--;
        System.out.println("线程" + Thread.currentThread().getName() + " j为:" + j);
    }

    public int getData() {
        return j;
    }

    static class AddRunnable implements Runnable {
        MyData data;

        // step 2 线程使用该类的对象并调用类的方法对数据进行操作
        public AddRunnable(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.add();
        }
    }

    static class DecRunnable implements Runnable {
        MyData data;

        public DecRunnable(MyData data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.dec();
        }
    }
}
