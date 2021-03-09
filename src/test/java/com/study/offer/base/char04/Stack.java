package com.study.offer.base.char04;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/9 22:30
 */

/**
 * 数据结构-栈
 * 基于数组实现
 */
public class Stack<E> {
    private Object[] data;
    //栈的容量
    private int maxSize = 0;
    //栈顶的指针
    private int top = -1;

    public Stack(int initialSize) {
        if (initialSize >= 0) {
            this.maxSize = initialSize;
            data = new Object[initialSize];
            top = -1;
        } else {
            throw new RuntimeException("初始化大小不能小于0->" + initialSize);
        }
    }

    /**
     * 压入元素
     * 进栈，第一个元素top=0
     */
    public boolean push(Object e) {
        if (top == maxSize - 1) {
            throw new RuntimeException("栈已满，无法将元素入栈");
        } else {
            data[++top] = e;
            return true;
        }
    }

    /**
     * 弹出栈顶元素
     */
    public E pop() {
        if (top == -1) {
            throw new RuntimeException("栈为空！");
        } else {
            return (E) data[top--];
        }
    }

    /**
     * 查询数据
     */
    public E peek() {
        if (top == -1) {
            throw new RuntimeException("栈为空！");
        } else {
            return (E) data[top];
        }
    }
}
