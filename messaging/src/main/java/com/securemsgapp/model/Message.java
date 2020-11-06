package com.securemsgapp.model;

public class Message {
    private String body;
    private String name;
    public String getMessageBody()  {
        return this.body;
    }

    public void setBody(String msgBody)  {
        this.body = msgBody;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
