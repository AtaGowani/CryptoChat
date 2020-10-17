package com.securemsgapp.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.securemsgapp.model.Message;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${securemsgapp.rabbitmq.exchange}")
    private String exchange;

    @Value("${securemsgapp.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Message message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
        System.out.println("Send msg = " + message);

    }
}
