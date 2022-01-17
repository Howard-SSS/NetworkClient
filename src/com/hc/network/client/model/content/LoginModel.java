package com.hc.network.client.model.content;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.MessageType;
import com.hc.network.client.util.Collector;

import java.io.Serializable;

public class LoginModel extends TextModel implements Serializable {
    public LoginModel() {
        super(Collector.name + "login");
        messageType = MessageType.LOGIN;
    }
}
