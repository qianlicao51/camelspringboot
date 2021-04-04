package com.study.config;

import com.rabbitmq.client.Channel;
import com.study.bean.SendMsg;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/4 11:16
 */
@Configuration
public class MyMQConfig {
    //监听过期消息
    @RabbitListener(queues = {"order.release.order.queue"})
    public void listener(SendMsg sendMsg, Channel channel, Message message) {
        System.out.println("收到过期的信息:" + sendMsg);
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
        }
    }
    //@Bean 的方式创建 Binding Queue Exchange

    /**
     * 容器中的 Binging Queue Exchange 都是自动创建(RabbitMQ中没有)
     */
    @Bean
    public Queue orderDelayQueue() {
        Map<String, Object> arguments = new HashMap<>(4);
        // Message TTL = 30s
        arguments.put("x-message-ttl", 60 * 1000);
        // Max length = 1 million
        arguments.put("x-max-length", 1000000);
        //死信路由到死信交换器DLX
        arguments.put("x-dead-letter-exchange", "order-event-exchange");
        arguments.put("x-dead-letter-routing-key", "order.release.order");
        //String name, boolean durable, boolean exclusive, boolean autoDelete
        return new Queue("order.delay.queue", true,
                false, false, arguments);
    }

    @Bean
    public Queue orderReleaseOrderQueue() {
        return new Queue("order.release.order.queue", true,
                false, false);
    }

    @Bean
    public Exchange orderEventExchange() {
        //String name, boolean durable, boolean autoDelete,
        return new TopicExchange("order-event-exchange", true, false);
    }

    @Bean
    public Binding orderCreateBinding() {
        //destination, DestinationType destinationType, String exchange, String routingKey,
        return new Binding("order.delay.queue", Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.create.order", null);
    }

    @Bean
    public Binding orderReleaseBinding() {
        //destination, DestinationType destinationType, String exchange, String routingKey,
        return new Binding("order.release.order.queue", Binding.DestinationType.QUEUE,
                "order-event-exchange",
                "order.release.order", null);
    }
}
