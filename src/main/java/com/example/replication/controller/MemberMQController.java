package com.example.replication.controller;

import com.example.replication.rabbitMQ.producer.RabbitProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberMQController {
    private final RabbitProducer rabbitProducer;

    @GetMapping("/mq")
    public String sendMsg() {
        rabbitProducer.sendMsg();
        return "sent";
    }


}
