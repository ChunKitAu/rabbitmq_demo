package com.chunkit.rabbitmq_productor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 9:50 AM
 **/
@Configuration
public class TopicConfig {

    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean
    public Queue firstQueue(){
        return new Queue(TopicConfig.man);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(TopicConfig.woman);
    }

    @Bean
    public TopicExchange testTopicExchange(){
        return new TopicExchange("TestTopicExchange");
    }

    //将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
    //这样只要是消息携带的路由键是topic.man,才会分发到该队列
    @Bean
    public Binding TestBindTopic1(){
        return BindingBuilder.bind(firstQueue()).to(testTopicExchange()).with(man);
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    public Binding TestBindTopic2(){
        return BindingBuilder.bind(secondQueue()).to(testTopicExchange()).with("topic.#");
    }


}
