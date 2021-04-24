package com.study;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/24 14:41
 */
public class TestLock implements Runnable {
    private LockStr lockStr;

    public TestLock(LockStr lockStr) {
        this.lockStr = lockStr;
    }

    /**
     * lock锁的究竟是什么东西
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        final LockStr lock = new LockStr("lock");
        final LockStr lock2 = new LockStr("lock");
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                threads[i] = new Thread(new TestLock(lock));
                threads[i].start();
            } else {
                threads[i] = new Thread(new TestLock(lock2));
                threads[i].start();
            }

        }

    }

    @Override
    public void run() {
        synchronized (lockStr) {
            System.out.println("[" + Thread.currentThread().getName() + "]开始运行了");
            // 休眠5秒模拟脚本调用

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("[" + Thread.currentThread().getName() + "]结束运行了");
        }
    }
}
