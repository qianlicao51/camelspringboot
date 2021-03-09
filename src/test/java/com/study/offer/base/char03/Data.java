package com.study.offer.base.char03;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 20:44
 */
public class Data implements Comparable<Data> {
    private String id;
    //排序字段
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public int compareTo(Data o) {
        return this.number.compareTo(o.getNumber());
    }

    public void demo() {
        //TODO 定义可排序的阻塞队列，根据data的number属性大小排序
        final PriorityBlockingQueue<Data> data = new PriorityBlockingQueue<>();
    }
}
