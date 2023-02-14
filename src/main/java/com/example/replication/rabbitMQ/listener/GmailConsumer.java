package com.example.replication.rabbitMQ.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"sms.fanout.queue"})
public class GmailConsumer {
    @RabbitHandler
    public void receiveMsg(String msg){
        System.out.println("sms.fanout.queue ---接收到的订单信息是：->" + msg);
    }

}
