package com.securemsgapp.controller;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.securemsgapp.model.Message;
import com.securemsgapp.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/msg-api")
public class MessageController {

    @Autowired
    private AmqpAdmin admin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${securemsgapp.rabbitmq.exchange}")
    private String exchange;

    @PostMapping(value = "/send")
    public String sendMessage(@RequestBody String recipientName) {
        // Queue args: durable = false, exclusive = false, autoDelete = true
        Queue queue = new Queue(recipientName, false, false, true);
        Binding binding = new Binding(recipientName, Binding.DestinationType.QUEUE, exchange, "user1", null);
        admin.declareQueue(queue);
        admin.declareBinding(binding);
        amqpTemplate.convertAndSend(exchange, "user1", "hey");

        return "Message sent";
    }
}
