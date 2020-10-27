package com.chunkit.rabbitmq_productor;

import com.chunkit.rabbitmq_productor.service.DirectSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 9:20 AM
 **/
@SpringBootTest
public class TestDirectSender {

    @Autowired
    DirectSender sender;

    @Test
    public void test(){
        sender.sender("TestDirect");
    }


}
