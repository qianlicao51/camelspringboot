package com.study.wwj.api.char04;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 11:28
 */
//增加泛型约束，每一个被加入都必须实现 Comparable
public class MyPriorityList<E extends Comparable<E>> {
    //增加了Comparator接口属性
    private final Comparator<E> comparator;
    private Node<E> header;
    private int size;

    public MyPriorityList(Comparator<E> comparator) {
        this.comparator = Objects.requireNonNull(comparator);
        this.header = null;
    }

    public static void main(String[] args) {
        final MyPriorityList<Integer> list = new MyPriorityList<>((o1, o2) -> o1 - o2);
        list.add(45);
        System.out.println(list);
        System.out.println("================================");
        list.add(456);
        list.add(4);
        list.add(48);
        list.add(500);
        System.out.println(list);
        System.out.println(list.popFirst());
        System.out.println(list);
    }

    public void add(E e) {
        //定义新的节点，其指向下一个节点的引用为null
        final Node<E> newNode = new Node<>(e);
        //当前链表节点引用
        Node<E> currentNode = this.header;
        //上一个节点的引用,初始为null,稍后的计算会得到
        Node<E> previous = null;
        //循环遍历，
        while (currentNode != null && e.compareTo(currentNode.getValue()) > 0) {
            //前一个节点为当前节点
            previous = currentNode;
            currentNode = currentNode.getNext();
        }
        if (previous == null) {
            //链表为空，链表的当前节点引用直接作为新构造的节点
            this.header = newNode;
        } else {
            previous.setNext(newNode);
        }
        //新的下一个节点为current
        newNode.setNext(currentNode);
        this.size++;
    }

    //判断当前链表是否为空
    public boolean isEmpty() {
        //只需要判断当前节点引用是否为null即可
        return header == null;
    }

    public E peekFirst() {
        // 如果为空则直接抛出异常
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("the linked list is empty");
        }
        //返回当前节点的元素数据
        return header.getValue();
    }

    public E popFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("the linked list is empty");
        }
        //获取当前节点数据，作为方法的最终返回值
        final E value = header.getValue();
        //将链表的 当前引用指向当前节点的下一个
        this.header = header.getNext();
        //元素减一
        this.size--;
        return value;
    }

    //获取当前链表的元素个数
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        Node<E> node = this.header;
        final StringBuilder builder = new StringBuilder("[");
        while (node != null) {
            builder.append(node.getValue().toString()).append(",");
            node = node.getNext();
        }
        if (builder.length() > 1) {
            builder.deleteCharAt(builder.length() - 1);
        }
        builder.append("]");
        return builder.toString();
    }

    /**
     * Node节点的泛型类型同样增加了香瓜约束，并且取缔了value和next字段不可变的特性
     */
    private static class Node<T extends Comparable<T>> {
        private T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this(value, null);
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
