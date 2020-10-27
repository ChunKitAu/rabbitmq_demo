package com.chunkit.rabbitmq_consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 9:33 AM
 **/
@Component
@RabbitListener(queues = {"TestDirectQueue"})
public class DirectReceiver {

    @RabbitHandler
    public void receiver(Map msg){
        System.out.println("DirectReceiver 消费者收到消息 ："+ msg.toString());
    }

}
