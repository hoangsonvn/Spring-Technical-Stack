package com.example.replication.rabbitMQ.producer;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 一一哥Sun
 * @Date Created in 2020/4/16
 * @Description Description
 */
@Slf4j
@Service
public class RabbitProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg() {
        //Message msg=new Message();
        //template.send(msg);

        //第一个参数是路由键
        String content = "hello," + new Date();
        log.warn("消息发送--->content={}", content);
        rabbitTemplate.convertAndSend("duanxin.fanout.queue", content);
    }


}

