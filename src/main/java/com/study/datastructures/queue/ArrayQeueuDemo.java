package com.study.datastructures.queue;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/21 23:23
 */
public class ArrayQeueuDemo {

    public static void main(String[] args) {
        final ArrayQueue queue = new ArrayQueue(3);
        queue.add(1);
        queue.add(2);
        queue.add(5);
        queue.show();

        queue.add(8);
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
        System.out.println(queue.getQueue());
    }
}

//使用数组模拟一个队列
class ArrayQueue {
    //最大长度
    private int maxSize;
    //队列头
    private int front;
    //队列尾部
    private int rear;

    private int[] arr;

    public ArrayQueue(int maxSize) {
        if (maxSize >= 0) {
            this.maxSize = maxSize;
            arr = new int[maxSize];
            front = rear = -1;
        } else {
            throw new RuntimeException("初始化长度不能小于0");
        }
    }

    public boolean isFully() {
        return rear == maxSize - 1;
    }

    private boolean isEmpty() {
        return rear == front;
    }

    public void add(int n) {
        if (isFully()) {
            System.out.println("队列满了");
            return;
        }
        // rear后移
        rear++;
        arr[rear] = n;
    }

    /**
     * 出队列
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        front++;
        return arr[front];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，无法遍历");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
