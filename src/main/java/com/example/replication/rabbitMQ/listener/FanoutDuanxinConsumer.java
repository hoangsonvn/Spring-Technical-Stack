package com.example.replication.rabbitMQ.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = {"duanxin.fanout.queue"})
public class FanoutDuanxinConsumer {

    @RabbitHandler
    public void receiveMsg(String msg){
        System.out.println("FanoutDuanxinConsumer ---接收到的订单信息是：->" + msg);
    }
}