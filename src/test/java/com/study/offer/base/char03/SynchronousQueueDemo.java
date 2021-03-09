package com.study.offer.base.char03;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 21:30
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Producter(queue).start();
        new Customer(queue).start();
    }

    //生产者线程
    static class Producter extends Thread {
        SynchronousQueue<Integer> queue;

        public Producter(SynchronousQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final int product = new Random().nextInt(1000);
                    queue.put(product);
                    System.out.println("product a data " + product);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(queue.isEmpty());
            }
        }
    }

    //消费者线程
    static class Customer extends Thread {
        SynchronousQueue<Integer> queue;

        public Customer(SynchronousQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final Integer data = queue.take();
                    System.out.println("customer a data:" + data);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
