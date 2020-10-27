package com.chunkit.rabbitmq_consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 10:22 AM
 **/
@Component
public class FanoutReceiver {

    @RabbitListener(queues = "fanout.A")
    @RabbitHandler
    public void receiverA(Map msg){
        System.out.println("Fanout A 收到消息:"+msg.toString());
    }

    @RabbitListener(queues = "fanout.B")
    @RabbitHandler
    public void receiverB(Map msg){
        System.out.println("Fanout B 收到消息:"+msg.toString());
    }

    @RabbitListener(queues = "fanout.C")
    @RabbitHandler
    public void receiverC(Map msg){
        System.out.println("Fanout C 收到消息:"+msg.toString());
    }

}
