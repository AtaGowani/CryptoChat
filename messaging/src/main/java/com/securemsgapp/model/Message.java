package com.securemsgapp.model;

public class Message {
    private String body;
    private String recipientName;
    private String senderName;

    public String getBody()  {
        return this.body;
    }
    public void setBody(String msgBody)  {
        this.body = msgBody;
    }

    public String getRecipientName() {
        return this.recipientName;
    }
    public void setRecipientName(String name) {
        this.recipientName = name;
    }

    public String getSenderName()  {  return this.senderName;  }
    public void setSenderName(String name)  {  this.senderName = name;  }
}
