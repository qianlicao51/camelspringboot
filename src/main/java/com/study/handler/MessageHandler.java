package com.study.handler;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;

/**
 * @author MI
 * @version 1.0
 * @date 2021/1/9 11:04
 */
public interface MessageHandler {
    /**
     * handler 处理类 接口
     *
     * @param exchange 消息
     */
    @Handler
    void handler(Exchange exchange) throws InterruptedException;
}
