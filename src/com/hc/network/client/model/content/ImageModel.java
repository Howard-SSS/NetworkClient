package com.hc.network.client.model.content;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.MessageType;

import java.io.Serializable;

public class ImageModel extends MessageAbstract implements Serializable {
    private byte[] bytes;
    public ImageModel() {
        super();
        messageType = MessageType.IMAGE;
    }
    public ImageModel(byte[] bytes) {
        this();
        this.bytes = bytes;
    }
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
    public byte[] getBytes() {
        return bytes;
    }
}
