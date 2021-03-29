package com.study.wwj.thread.char24;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 16:40
 */
public class Operator {
    public void call(String business) {
        //为每一个请求创建一个线程去处理
        TaskHandler taskHandler = new TaskHandler(new Request(business));
        new Thread(taskHandler).start();
    }
    //使用线程池替每一个请求创建线程
}
