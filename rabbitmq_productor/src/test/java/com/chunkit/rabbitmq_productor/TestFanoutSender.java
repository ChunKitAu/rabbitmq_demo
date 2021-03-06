package com.chunkit.rabbitmq_productor;

import com.chunkit.rabbitmq_productor.service.FanoutSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 10:24 AM
 **/
@SpringBootTest
public class TestFanoutSender {

    @Autowired
    FanoutSender sender;

    @Test
    public void test(){
        sender.send("Test Fanout");
    }

}
