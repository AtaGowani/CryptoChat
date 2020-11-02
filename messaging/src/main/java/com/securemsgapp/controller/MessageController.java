package com.securemsgapp.controller;

import com.securemsgapp.model.Message;
import com.securemsgapp.model.MessageLists;
import com.securemsgapp.service.RabbitMQListener;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    private MessageLists messageListManger = new MessageLists();

    //private RabbitMQListener listener = new RabbitMQListener();

    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam("to") String recipientName,@RequestParam("msg") String msg) {
        // Queue args: durable = false, exclusive = false, autoDelete = true
        Queue queue = new Queue(recipientName, true, false, false);
        Binding binding = new Binding(recipientName, Binding.DestinationType.QUEUE, exchange, recipientName, null);
        admin.declareQueue(queue);
        admin.declareBinding(binding);
        messageListenerContainer.addQueues(queue);
        Message message = new Message();
        message.setBody(msg);
        messageListManger.addItemToMessageList(message);
        amqpTemplate.convertAndSend(exchange, recipientName, message);
        return "Message sent";
    }

    @GetMapping(value = "/mailbox")
    public ArrayList<JSONObject> getMailbox() {
        MessageController controller = new MessageController();
        controller.convertTOJson(messageListManger);
        return messageListManger.getjsonMessageList();
    }

    public void convertTOJson(MessageLists messagelists) {
        for (int i = 0; i < messagelists.getMessageList().size(); i++) {
            JSONObject json = new JSONObject();
            json.put("body", messagelists.getMessageList().get(i));
            messagelists.addItemTojsonMessageList(json);

        }
    }
}
