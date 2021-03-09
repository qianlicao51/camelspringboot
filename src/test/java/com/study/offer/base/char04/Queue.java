package com.study.offer.base.char04;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/9 22:44
 */
public class Queue<E> {
    private Object[] data = null;
    //队列的容量
    private int maxSize;

    //队列头，允许删除
    private int front;

    //队列尾 允许插入
    private int rear;

    public Queue(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            front = rear = 0;
        } else {
            throw new RuntimeException("初始化大小不能小于0");
        }
    }

    /**
     * 插入数据
     */
    public boolean add(E e) {
        if (rear == maxSize) {
            throw new RuntimeException("队列已满");
        } else {
            data[rear++] = e;
            return true;
        }
    }

    public boolean empty() {
        return rear == maxSize;
    }
}
