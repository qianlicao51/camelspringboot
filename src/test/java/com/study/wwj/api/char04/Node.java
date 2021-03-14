package com.study.wwj.api.char04;

import lombok.Getter;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 10:09
 */
@Getter
public class Node<T> {
    //数据属性
    private final T value;
    //指向下一个节点的引用
    private final Node<T> next;

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }

}
