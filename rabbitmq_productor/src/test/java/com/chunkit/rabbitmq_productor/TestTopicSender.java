package com.chunkit.rabbitmq_productor;

import com.chunkit.rabbitmq_productor.config.TopicConfig;
import com.chunkit.rabbitmq_productor.service.TopicSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 10:01 AM
 **/
@SpringBootTest
public class TestTopicSender {

    @Autowired
    TopicSender sender;

    @Test
    public void test(){
//        sender.sendTopic("Topic:Man", TopicConfig.man);
        sender.sendTopic("Topic:Woman",TopicConfig.woman);
    }


}
