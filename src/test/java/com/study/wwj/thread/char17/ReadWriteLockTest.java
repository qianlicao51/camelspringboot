package com.study.wwj.thread.char17;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/8 11:10
 */
public class ReadWriteLockTest {
    private final static String text = "Thisistheexampleforreadwritelock";

    public static void main(String[] args) {
        //    定义共享数据
        final ShareData shareData = new ShareData(50);
        //2个线程进行数据写操作
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < text.length(); j++) {
                    final char c = text.charAt(j);
                    try {
                        shareData.write(c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //10个线程进行数据读操作
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread() + " read " + new String(shareData.read()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
