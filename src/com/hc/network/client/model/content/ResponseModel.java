package com.hc.network.client.model.content;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.MessageType;
import com.hc.network.client.util.Collector;

import java.io.Serializable;
import java.net.InetSocketAddress;

public class ResponseModel extends MessageAbstract implements Serializable {
    protected InetSocketAddress chatAddress;
    private int headNum;
    public ResponseModel() {
        super();
        messageType = MessageType.RESPONSE;
        chatAddress = Collector.chatAddress;
        this.headNum = 0;
    }
    public ResponseModel(int headNum) {
        this();
        this.headNum = headNum;
    }
    public void setHeadNum(int headNum) {
        this.headNum = headNum;
    }
    public int getHeadNum() {
        return headNum;
    }
    public InetSocketAddress getChatAddress() {
        return chatAddress;
    }
}
