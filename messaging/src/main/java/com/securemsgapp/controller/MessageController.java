package com.securemsgapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securemsgapp.model.Message;
import com.securemsgapp.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/msg-api")
public class MessageController {
    @Autowired
    RabbitMQSender rabbitMQSender;

    @PostMapping(value = "/send")
    public String sendMessage() {
        Message message = new Message();
        message.setBody("hi");
        rabbitMQSender.send(message);

        return "Message sent";
    }
}
