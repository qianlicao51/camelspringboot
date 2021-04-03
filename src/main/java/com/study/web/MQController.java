package com.study.web;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.study.bean.SendMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MI
 * @version 1.0
 * @date 2021/4/3 23:17
 */
@Slf4j
@RestController
@RequestMapping("/mq")
public class MQController {
    //交换机
    final String exchange = "hello-java-exchange";
    //路由键
    final String routingKey = "hello.java";
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send")
    public String sendMessage() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                rabbitTemplate.convertAndSend(exchange, routingKey, new SendMsg(IdWorker.getIdStr()));
            } else {
                rabbitTemplate.convertAndSend(exchange, "hello2.java", new SendMsg(IdWorker.getIdStr()));

            }
        }
        return "ok";
    }
}
