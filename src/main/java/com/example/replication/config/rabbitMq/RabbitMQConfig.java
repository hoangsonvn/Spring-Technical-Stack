package com.example.replication.config.rabbitMq;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 尹稳健~
 * @version 1.0
 */
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.host}")
    private String host;



    // 声明注册交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange");
    }

    // 声明队列
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.fanout.queue", true);
    }

    @Bean
    public Queue duanxinQueue() {
        return new Queue("duanxin.fanout.queue", true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue", true);
    }

    // 交换机和队列绑定
    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding duanxinBinding() {
        return BindingBuilder.bind(duanxinQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setUseDirectReplyToContainer(false);
        return rabbitTemplate;
    }
}

