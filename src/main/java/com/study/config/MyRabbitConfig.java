package com.study.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/3 20:47
 */
// @Configuration
public class MyRabbitConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * JSON序列化 机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 定制 rabbitTemplate
     * 1 ## 开启发送端确认(过时了,要使用 publisher-confirm-type)
     * #spring.rabbitmq.publisher-confirms=true
     * spring.rabbitmq.publisher-confirm-type=correlated
     * <p>
     * 2 消息正确抵达队列进行回调
     * <code>
     * # 开启发送端消息抵达队列确认
     * spring.rabbitmq.publisher-returns=true
     * # 只要消息抵达队列，以异步发送优先回调我们这个returnconfirm
     * spring.rabbitmq.template.mandatory=true
     * </code>
     * 3 消费端确认(保证每个消息被正确消费，此时才可以broker删除这个消息)
     * <code>
     * ### 手动 ACK 消息
     * spring.rabbitmq.listener.simple.acknowledge-mode=manual</code>
     * <code>
     * <span>
     * 3.1 默认是自动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
     * 问题：宕机发生消息丢失
     * 手动模式，消息一直是unacked状态，即使cosumer宕机，消息不会丢失，会重新变为ready，
     * </span>
     * 3.2 如何签收
     * ----|channel.basicAck(deliveryTag, false);签收，业务成功完成
     * ----|channel.basicNack(deliveryTag, false, true);拒签，业务失败
     *
     * </code>
     */
    @PostConstruct//对象创建完成后会调用
    public void initRabbitTemplate() {
        //设置确认回调
        /**
         * 1 只要消息抵达Broker，ack=true
         * correlationData:当前消息的唯一关联数据
         * ack: 消息是否成功收到
         * cause: 失败原因
         */
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("confirm...correlationData=" + correlationData
                    + " ack=" + ack + " cause=" + cause);
        });

        //设置消息抵达队列的确认回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定的队列，就会触发这个失败回调
             * @param message 投递失败的消息，详细信息
             * @param replyCode 回复的状态码
             * @param replyText 回复的文本内容
             * @param exchange 当时这个消息发送给哪个交换机
             * @param routingKey 当时这个消息用哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println(String.format("Fail Message=%s==>replyCode=%s==>replyText=%s==>exchange=%s==>routingKey=%s"
                        , message, replyCode, replyText, exchange, routingKey));
            }
        });
    }
}
