package com.study.service;

import com.rabbitmq.client.Channel;
import com.study.bean.SendMsg;
import com.study.bean.SendMsgDate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Service;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/3 21:01
 */
@Service
// @RabbitListener(queues = {"hello-java-queue"})
public class RabbitListenerService {
    /**
     * 1 Message 原生消息，头+体(org.springframework.amqp.core.Message)
     * 2 T<发送的消息类型></>直接写类型(给定一个无参构造函数)|而jackson的反序列化需要无参构造函数https://blog.csdn.net/qq_22067469/article/details/85255826
     * 3 com.rabbitmq.client.Channel 当前传输数据的通道
     * <p>
     * Queue: 可以有很多人同时监听，只要收到消息，队列就删除消息。而且只能有一个收到消息
     * 场景
     * --1) 服务启动多个
     * --2) 只有一个消息完全处理完，方法运行结束，才能接受下一个消息
     */

    @RabbitHandler
    public void receiveMsg(Message msg, SendMsg sendMsg, Channel channel) throws InterruptedException {
        // 接受到消息(Body:'{"msg":"SendMsg","date":1617455823955}' MessageProperties [headers={__TypeId__=com.study.SendMsg}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=hello-java-exchange, receivedRoutingKey=hello.java, deliveryTag=1, consumerTag=amq.ctag-lP8Uu5yaGfI3b8Uj9Z-BLA, consumerQueue=hello-java-queue])
        System.out.println(Thread.currentThread().getName() + "接受到消息" + msg.getMessageProperties());
        System.out.println(Thread.currentThread().getName() + "消息处理完成");
    }

    /**
     * RabbitHandler 配合RabbitListener 使用，可以区分不同的消息。
     */
    @RabbitHandler
    public void receiveMsg2(Message msg, SendMsgDate sendMsg, Channel channel) throws InterruptedException {
        System.out.println("消息处理完成" + sendMsg);
    }
}


