package com.study.wwj.thread.char24;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/19 16:36
 */
//Thread-pre-Message模式
//客户提交的业务请求
public class Request {
    private final String business;

    public Request(String business) {
        this.business = business;
    }

    @Override
    public String toString() {
        return business;
    }
}
