package com.study;

// import com.study.bean.SendMsg;
import com.study.bean.SendMsg;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/3 19:59
 */
@Slf4j
// @SpringBootTest
public class RabbitApplicationTest {
    //交换机
    final String exchange = "hello-java-exchange";
    //路由键
    final String routingKey = "hello.java";
    @Autowired
    AmqpAdmin amqpAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void sendMsg() {
        //发送消息
        String msg = "SendMsg";
        //发送的是对象，必须序列化
        // rabbitTemplate.convertAndSend(exchange, routingKey, new SendMsg("sendMsg"));
        //使用JSON 形式发送{org.springframework.amqp.support.converter.Jackson2JsonMessageConverter}
        //发送的实体给一个无参构造函数|而jackson的反序列化需要无参构造函数https://blog.csdn.net/qq_22067469/article/details/85255826
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(exchange, routingKey, new SendMsg(msg));
            log.info("消息发送成功{}", msg);
        }
    }

    /**
     * 1 创建队列 exchange   Queue Binging
     * 2 发送消息
     */
    @Test
    void createExchange() {
        final DirectExchange directExchange = new DirectExchange(exchange, true, false);
        amqpAdmin.declareExchange(directExchange);
        log.info("exchange[{}]创建成功", "hello-java-exchange");
    }

    @Test
    void createQueue() {
        final DirectExchange directExchange = new DirectExchange(exchange, true, false);
        //name 持久化，排他队列,不自动删除
        final Queue queue = new Queue("hello-java-queue", true, false, false);
        amqpAdmin.declareQueue(queue);
        log.info("Queue[{}]创建成功", "hello-java-queue");
    }

    @Test
    void binding() {
        //  destination, 目的地{交换机、队列}
        //  destinationType,  目的地类型
        //  exchange,   交换机
        //  routingKey  路由键
        final Binding binding = new Binding("hello-java-queue",
                Binding.DestinationType.QUEUE, exchange, routingKey, null
        );
        amqpAdmin.declareBinding(binding);
        log.info("Binding[{}]创建成功", "hello-java-queue");
    }
}
