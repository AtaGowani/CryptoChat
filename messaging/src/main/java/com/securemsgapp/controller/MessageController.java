package com.securemsgapp.controller;

import org.json.simple.JSONArray;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.securemsgapp.model.Message;
import com.securemsgapp.model.MessageManager;

@RestController
@RequestMapping(value = "/msg-api")
public class MessageController {

    @Autowired
    private AmqpAdmin admin;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private DirectMessageListenerContainer messageListenerContainer;

    @Value("${securemsgapp.rabbitmq.exchange}")
    private String exchange;
	
	private MessageManager messageManager = new MessageManager();

    @PostMapping(value = "/send")
    public ResponseEntity<String> sendMessage(@RequestParam("to") String recipientName, @RequestParam("from") String senderName, @RequestParam("msg") String msg) {
        // Queue args: durable = false, exclusive = false, autoDelete = true
        Queue queue = new Queue(recipientName, true, false, false);
        Binding binding = new Binding(recipientName, Binding.DestinationType.QUEUE, exchange, recipientName, null);
        admin.declareQueue(queue);
        admin.declareBinding(binding);
        messageListenerContainer.addQueues(queue);
        Message message = new Message();
        message.setSenderName(senderName);
        message.setRecipientName(recipientName);
        message.setBody(msg);
        messageManager.add(message);
        amqpTemplate.convertAndSend(exchange, recipientName, message);
        return new ResponseEntity<>("Message sent", HttpStatus.OK);
    }
    @GetMapping(value = "/mailbox")
    public JSONArray getMailbox(@RequestParam("to") String recipientName) {
       return messageManager.get(recipientName);
    }

}
