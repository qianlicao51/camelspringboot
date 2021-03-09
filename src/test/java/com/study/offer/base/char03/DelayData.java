package com.study.offer.base.char03;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/6 20:58
 */
public class DelayData implements Delayed {
    //延迟队列排序字段
    private Integer number;
    // 设置队列5s延迟获取
    private long delayTime;

    public static void main(String[] args) {
        // 创建延迟队列
        final DelayQueue<DelayData> queue = new DelayQueue<>();
        // 实时添加数据
        queue.add(new DelayData());
        while (true) {
            try {
                final DelayData take = queue.take();
            } catch (InterruptedException e) {
            }
        }
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.delayTime;
    }

    @Override
    public int compareTo(Delayed o) {
        DelayData compare = (DelayData) o;
        return this.number.compareTo(compare.getNumber());
    }
}
