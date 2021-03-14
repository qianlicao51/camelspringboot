package com.study.wwj.api.char04;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 10:18
 */
public class MyList<E> {
    //当前节点引用
    private Node<E> header;
    private int size;

    public MyList() {
        //当前元素节点为指向NULL的属性
        header = null;
    }

    public static void main(String[] args) {
        final MyList<Integer> list = new MyList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        //对list进行测试
        System.out.println(list);
        System.out.println(list.size);
        System.out.println(list.peekFirst());
        System.out.println("================================");
        System.out.println(list.popFirst());
        System.out.println(list.size);
        System.out.println(list);
        System.out.println(list.peekFirst());
        System.out.println(list.popFirst());
        System.out.println(list.popFirst());
        System.out.println(list.popFirst());
        System.out.println(list.popFirst());
        System.out.println("================================");
        System.out.println(list.isEmpty());
        System.out.println(list.size);
        System.out.println(list);
        System.out.println("================================");
    }

    //清空 链表中的所有元素
    public void clear() {
        //显示设定当前size=0
        this.size = 0;
        //将当前节点引用设置为null即可，由于其他元素ROOT不可达，因此在稍后的垃圾回收中将被回收
        this.header = null;
    }

    public void add(E e) {
        //定义新的节点node，并且将其next引用指向当前节点所引用的header
        final Node<E> node = new Node<>(e, header);
        this.header = node;
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
}
