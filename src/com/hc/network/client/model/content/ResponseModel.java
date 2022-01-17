package com.hc.network.client.model.content;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.MessageType;
import com.hc.network.client.network.NetworkType;
import com.hc.network.client.util.Collector;

import java.io.Serializable;
import java.net.InetSocketAddress;

public class ResponseModel extends MessageAbstract implements Serializable {
    protected InetSocketAddress chatAddress;
    public ResponseModel() {
        super();
        messageType = MessageType.RESPONSE;
        chatAddress = Collector.getAddress(NetworkType.MSOCKET);
    }
    public ResponseModel(int headNum) {
        this();
    }
    public InetSocketAddress getChatAddress() {
        return chatAddress;
    }
}
