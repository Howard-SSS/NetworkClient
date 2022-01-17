package com.hc.network.client.model.content;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.MessageType;

import java.io.Serializable;

public class TextModel extends MessageAbstract implements Serializable {
    private String text;
    public TextModel() {
        super();
        messageType = MessageType.TEXT;
    }
    public TextModel(String text) {
        this();
        setText(text);
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
