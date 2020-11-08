package com.securemsgapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageManager {

    private ArrayList<Message> messageList = new ArrayList<>();
    private ArrayList<JSONObject> jsonMessageList = new ArrayList<>();
    private HashMap<String, ArrayList<JSONObject>> messageQueues = new HashMap<>();

    public void add(Message message)  {
        String recipient = message.getRecipientName();
        this.messageQueues.putIfAbsent(recipient, new ArrayList<>());   // add queue slot for new user
        this.messageQueues.get(recipient).add(MessageManager.toJSON(message));  // add message into unique queue slot
    }

    public JSONArray get(String recipient)  {
        JSONArray jsonArray = new JSONArray();

        if (this.messageQueues.containsKey(recipient))  {
            for (JSONObject jsonObj : this.messageQueues.get(recipient))  {
                jsonArray.add(jsonObj);
            }
        }
        this.messageQueues.remove(recipient);   // remove already delivered messages to avoid duplicates
        return jsonArray;
    }

    public static JSONObject toJSON(Message message)  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("from", message.getSenderName());
        jsonObject.put("to", message.getRecipientName());
        jsonObject.put("body", message.getBody());
        return jsonObject;
    }

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
