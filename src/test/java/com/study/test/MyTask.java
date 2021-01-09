package com.study.test;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 22:32
 */
public class MyTask implements Runnable {
    /**
     * 任务编号
     */
    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 即将执行任务:" + id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 完成任务:" + id);

    }

    @Override
    public String toString() {
        return "MyTask{" +
                "id=" + id +
                '}';
    }
}
