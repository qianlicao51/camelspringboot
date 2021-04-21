package com.study;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

/**
 * @author Administrator
 * 使用rabbitmq
 * 1. 引入场景启动器 RabbitAutoConfiguration
 * 2 配置信息
 * <p>
 * 3 配置spring.rabbitmq.*
 * 4 启用enableRabbit
 * 4 监听消息 使用 @RabbitmqListener 必须有EnableRabbit
 * 5 监听消息 使用 RabbitListener 必须有@EnableRabbit
 * -- @RabbitListener 类和方法上
 * -- @RabbitHandler 方法上，重载区分不同的消息
 */
@SpringBootApplication(exclude = {RabbitAutoConfiguration.class})
// @SpringBootApplication
@EnableRabbit
public class CamelspringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamelspringbootApplication.class, args);
    }
}
