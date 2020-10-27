package com.chunkit.rabbitmq_productor.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 9:57 AM
 **/
@Component
public class TopicSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendTopic(String msg,String routingKey){
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("createTime",createTime);
        map.put("message",msg);
        rabbitTemplate.convertAndSend("TestTopicExchange",routingKey,map);
    }


}
