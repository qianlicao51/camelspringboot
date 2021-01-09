package com.study.test;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 22:59
 * 测试，
 * 1 创建线程池类对象
 * 2 提交多个任务
 */
public class MyTest {

    public static void main(String[] args) {

        final MyThreadPool pool = new MyThreadPool(2, 4, 20);
        for (int i = 0; i < 30; i++) {
            // 创建任务对象提交给线程池
            final MyTask myTask = new MyTask(i);
            pool.submit(myTask);
        }
    }
}
