package com.hc.network.client.model.content;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.MessageType;
import com.hc.network.client.util.Collector;

import java.io.Serializable;

public class QuitModel extends TextModel implements Serializable {
    public QuitModel() {
        super(Collector.name + "quit");
        messageType = MessageType.QUIT;
    }
}
