package com.study.wwj.thread.char03;

import java.util.List;

/**
 * @author study
 * @version 1.0
 * @date 2021/2/4 10:50
 */
public interface FightQuery {
    /**
     * 不管是thread的run方法，还是runnable接口，都是void返回类型，
     * 如果想通过某个线程的运行得到结果，就需要自己定义一个返回的接口
     */
    List<String> get();
}
