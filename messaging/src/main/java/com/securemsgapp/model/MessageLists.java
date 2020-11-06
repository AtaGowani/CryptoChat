package com.securemsgapp.model;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class MessageLists {

    private ArrayList<Message> messageList = new ArrayList<>();
    private ArrayList<JSONObject> jsonMessageList = new ArrayList<>();

    public ArrayList<Message>  getMessageList() {
        return this.messageList;
    }
    public void addItemToMessageList(Message message) {
        this.messageList.add(message);
    }

    public ArrayList<JSONObject>  getjsonMessageList() {
        return this.jsonMessageList;
    }
    public void addItemTojsonMessageList(JSONObject jObject) {
        this.jsonMessageList.add(jObject);
    }

    public void clearJsonList() {
        this.jsonMessageList = new ArrayList<>();
    }
}
