package com.study.wwj.api.char04;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * @author MI
 * @version 1.0
 * @date 2021/3/14 12:21
 */
public class SimpleSkipList {
    //每一层头结点的标记符
    private final static byte HEAD_BIT = (byte) -1;
    //每一层尾节点的标记符
    private final static byte TAIL_BIT = (byte) 1;
    //每一个数据节点的标记符
    private final static byte DATA_BIT = (byte) 0;
    //定义头部节点属性
    private Node head;
    //定义尾部节点属性
    private Node tail;
    //元素个数
    private int size;
    //定义跳表层高
    private int height;
    //随机数，主要用于随机的方式决定元素应该被放在第几层
    private Random random;

    public SimpleSkipList() {
        //初始化头部和尾部节点
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        //头部节点的右边为尾部节点
        this.head.right = tail;
        //尾部节点的左边为头部节点
        tail.left = head;
        random = new Random(System.currentTimeMillis());

    }

    private Node find(Integer element) {
        //从头节点开始寻找
        Node current = head;
        for (; ; ) {
            //当前节点的右节点不是尾结点，并且当前节点的右节点数据小于element
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                //继续朝右前行
                current = current.right;
            }
            //当 current 节点存在donw节点
            if (current.down != null) {
                //开始向下一层
                current = current.down;
            } else {
                //到达最高层，终止循环
                break;
            }
        }
        return current;
    }

    public void add(Integer element) {
        //根据element找到合适它的存储位置，也就是邻近的节点，需要注意的是，此刻节点在整个跳表的第一层
        Node nearNode = this.find(element);
        //定义一个新的节点
        Node newNode = new Node(element);
        //新节点的左节点为nearNode
        newNode.left = nearNode;
        //新节点的右节点为nearNode.right,相当于将新节点插入到了nearNode，和nearNode中间
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;
        //当前层级为0，代表最底层一层
        int currentLevel = 0;
        //根据随即判断是否将新节点放到新的层级，
        while (random.nextDouble() < 0.5d) {
            //如果currentLevels大于整个跳表的层高，则需要为跳表增加一层链表
            if (currentLevel >= height) {
                height++;
                //定义新层高 head 和 tail
                Node dumyHead = new Node(null, HEAD_BIT);
                Node dumyTail = new Node(null, TAIL_BIT);
                //指定新层高head和tail 的关系
                dumyHead.right = dumyTail;
                dumyHead.down = head;
                head.up = dumyHead;
                dumyTail.left = dumyHead;
                dumyTail.down = tail;
                tail.up = dumyTail;
                head = dumyHead;
                tail = dumyTail;
            }
            //在新的一层中增加 element 节点，同样要维护上下左右的关系
            while ((nearNode != null) && nearNode.up == null) {
                nearNode = nearNode.left;
            }
            nearNode = newNode.up;
            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = nearNode;
            nearNode.right.left = upNode;
            nearNode.right = upNode;
            nearNode.up = upNode;
            newNode = upNode;
            currentLevel++;
        }
        size++;
    }

    //元素节点类，该节点中只存放整数类型
    @Getter
    @Setter
    private static class Node {
        private Integer value;
        //每一个节点的周围节点的引用
        private Node up, down, left, right;
        //节点类型
        private byte bit;

        public Node(Integer value) {
            this(value, DATA_BIT);
        }

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        @Override
        public String toString() {
            return value + " bit:" + bit;
        }
    }
}
