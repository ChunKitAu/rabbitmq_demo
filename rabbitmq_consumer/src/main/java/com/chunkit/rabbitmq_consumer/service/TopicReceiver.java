package com.chunkit.rabbitmq_consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 10:05 AM
 **/
@Component
public class TopicReceiver {

    //只有topic.man的队列才能接收
    @RabbitListener(queues = "topic.man")
    @RabbitHandler
    public void receiverManTopic(Map msg){
        System.out.println("TopicManReceiver 消费者收到消息"+ msg.toString());
    }

    //topic.# 接收topic.  开头的队列的消息
    @RabbitListener(queues = "topic.woman")
    @RabbitHandler
    public void receiverTotalTopic(Map msg){
        System.out.println("TopicTotalReceiver 消费者收到消息"+ msg.toString());
    }

}
