package com.study.wwj.api.char03;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/9 11:37
 */
public class ExchangerExample2 {
    public static void main(String[] args) throws InterruptedException {
        //定义数据类型为string 的exchange
        final Exchanger<String> exchanger = new Exchanger<>();

        //定义 StringGenerator 线程
        final StringGenerator generator = new StringGenerator(exchanger, "Generator");
        //定义 StringConsumer 线程
        final StringConsumer consumer = new StringConsumer(exchanger, "Consumer");

        //分别启动线程
        generator.start();
        consumer.start();
        //休眠1分钟后关闭 两个线程
        TimeUnit.MINUTES.sleep(1);
        consumer.close();
        generator.close();

    }

    //定义 Closable 接口
    private interface Closable {
        //关闭方法
        void close();

        //判断当前线程是否被关闭
        boolean closed();
    }

    private abstract static class ClosableThread extends Thread implements Closable {
        protected final Exchanger<String> exchanger;
        private volatile boolean closed = false;

        public ClosableThread(Exchanger<String> exchanger, final String name) {
            super(name);
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            //当前线程未关闭时不断执行 doExchange 方法
            while (!closed) {
                this.doExchange();
            }
        }

        protected abstract void doExchange();

        //关闭当前线程
        @Override
        public void close() {
            System.out.println(Thread.currentThread() + " will be closed.");
            this.closed = true;
            this.interrupt();
        }

        @Override
        public boolean closed() {
            return this.closed || this.isInterrupted();
        }
    }

    private static class StringGenerator extends ClosableThread {
        private char initialChar = 'A';

        public StringGenerator(Exchanger<String> exchanger, String name) {
            super(exchanger, name);
        }

        private static void randomSleep() {
            try {
                TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
            } catch (InterruptedException e) {
            }
        }

        @Override
        protected void doExchange() {
            //模拟数据生成
            String str = "";
            for (int i = 0; i < 3; i++) {
                randomSleep();
                str += (initialChar++);
            }
            //① 如果当前线程未关闭，则执行 exchanger的 exchange方法
            if (!closed()) {
                try {
                    exchanger.exchange(str);
                } catch (InterruptedException e) {
                    //如果关闭了 执行close 方法，那么执行终端操作时会捕获到中断信号
                    System.out.println(currentThread() + " receive  the close sinnal .");
                }
            }
        }
    }

    private static class StringConsumer extends ClosableThread {
        public StringConsumer(Exchanger<String> exchanger, String name) {
            super(exchanger, name);
        }

        @Override
        protected void doExchange() {
            //② 如果线程未关闭，则执行 exchanger 的exchange方法
            try {
                if (!this.closed()) {
                    final String data = exchanger.exchange(null);
                    System.out.println("received the data :" + data);
                }
            } catch (InterruptedException e) {
                System.out.println(currentThread() + " receive  the close sinnal .");
            }
        }
    }
}
