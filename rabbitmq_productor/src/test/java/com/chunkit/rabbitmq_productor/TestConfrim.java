package com.chunkit.rabbitmq_productor;

import com.chunkit.rabbitmq_productor.service.DirectSender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 10:43 AM
 **/
@SpringBootTest
public class TestConfrim {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //消息推送到server，但是在server里找不到交换机
    //触发 ConfirmCallback
    @Test
    public void test(){
        send("message: non-existent-exchange test message ","non-existent-exchange");
    }

    //消息推送到server，找到交换机了，但是没找到队列
    //创建一个lonelyDirectExchange  但是不给他做任何绑定
    // 触发 confirmCallback 和 returnCallBack
    @Test
    public void test2(){
        send("message: lonelyDirectExchange  test message  ","lonelyDirectExchange");
    }

    //消息推送到server，交换机和队列啥都没找到  类似test1
    //触发confirmCallBack
    @Test
    public void test3(){
        send("message: no-exit-exchange and queue  test message  ","non-existent-exchange");
    }

    //正常推送
    //触发 ConfirmCallback
    @Test
    public void test4(){
        send("message: send  message success ","TestDirect");
    }

    public void send(String msg,String exchange){
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", msg);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend(exchange, "TestDirectRouting", map);
    }
}
