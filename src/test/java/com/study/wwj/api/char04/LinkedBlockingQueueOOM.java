package com.study.wwj.api.char04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/29 13:41
 */
//https://blog.csdn.net/guishuanglin/article/details/101284385 linkedBlockingQueue 并发问题
@Slf4j
public class LinkedBlockingQueueOOM {
    static LinkedBlockingQueue<String> receiveQueue = new LinkedBlockingQueue<String>();

    public static void main(String[] args) {
        receiveQueue.add("123");
        final ReceiveThread thread = new ReceiveThread(receiveQueue);
        new Thread(thread).start();

    }

}
