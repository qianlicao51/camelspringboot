package com.study.wwj.api.char04;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author study
 * @version 1.0
 * @date 2021/3/29 13:46
 */
@Slf4j
public class ReceiveThread implements Runnable {
    LinkedBlockingQueue<String> receiveQueue;

    public ReceiveThread(LinkedBlockingQueue<String> receiveQueue) {
        this.receiveQueue = receiveQueue;
    }

    @Override
    public void run() {
        String msg = null;
        while (true) {
            try {
                msg = receiveQueue.poll();
                if (msg != null) {
                    //处理消息....
                    log.info("接收数据:" + msg);
                } else {
                    log.info("null");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                log.error("接收线程异常", e);
            } finally {
            }
        }
    }
}
