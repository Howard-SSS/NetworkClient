package com.hc.network.client.model;

import com.hc.network.client.util.Collector;

public abstract class MessageAbstract {
    protected String id;
    protected  MessageType messageType;
    protected String name;
    public MessageAbstract() {
        name = Collector.name;
        id = Collector.userId;
    }
    public MessageType getMessageType() {
        return messageType;
    }

    public String getName() {
        return name;
    }

    /**
     * 以重写
     * @return id
     */
    @Override
    public String toString() {
        return id;
    }
}
