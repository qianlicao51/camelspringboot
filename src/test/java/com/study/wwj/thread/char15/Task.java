package com.study.wwj.thread.char15;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/5 14:37
 */
@FunctionalInterface
public interface Task<T> {
    //任务执行接口，该接口允许有返回值
    T call();
}
