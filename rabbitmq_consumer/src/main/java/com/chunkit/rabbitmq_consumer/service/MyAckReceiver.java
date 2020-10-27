package com.chunkit.rabbitmq_consumer.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ChunKit.Ou
 * @data 10/27/2020 11:08 AM
 **/
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try{
            //这里的message 是map 传递的，具体根据自己的业务修改
            String msg = message.toString();
            String[] msgArray = msg.split("'");
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim(), 3);
            String messageId=msgMap.get("messageId");
            String messageData=msgMap.get("messageData");
            String createTime=msgMap.get("createTime");
            System.out.println("  MyAckReceiver  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
            System.out.println("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
            //第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag,true);
        }catch (Exception e){
            //第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
            channel.basicReject(deliveryTag,false);
            e.printStackTrace();
        }
    }


    //{key=value,key=value,key=value} 格式转换成map
    //这里可以根据自己的业务修改
    private Map<String, String> mapStringToMap(String str, int entryNum ) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",",entryNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }
}
