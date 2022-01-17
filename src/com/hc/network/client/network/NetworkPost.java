package com.hc.network.client.network;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.content.LoginModel;
import com.hc.network.client.model.content.QuitModel;
import com.hc.network.client.model.content.TextModel;
import com.hc.network.client.util.Collector;
import com.hc.network.client.util.Translate;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * 网络收发器
 */
public abstract class NetworkPost implements AcceptImpl {
    private MulticastSocket multicastSocket;
    public NetworkPost() {
        try {
            multicastSocket = new MulticastSocket(Collector.getAddress(NetworkType.MSOCKET));
            multicastSocket.joinGroup(Collector.getAddress(NetworkType.MSOCKET).getAddress());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public MulticastSocket getMulticastSocket() {
        return multicastSocket;
    }
    // MARK: - send
    public boolean sendText(String s) {
        TextModel model = new TextModel(s);
        return totalSend(model);
    }
    public boolean sendLogin() {
        LoginModel model = new LoginModel();
        return totalSend(model);
    }
    public boolean sendQuit() {
        QuitModel model = new QuitModel();
        return totalSend(model);
    }
    private boolean totalSend(MessageAbstract model) {
        boolean ret = true;
        byte[] bytes = Translate.ObjectToByte(model);
        if (bytes == null)
            return false;
        DatagramPacket packet = new DatagramPacket(
                bytes,
                bytes.length,
                Collector.getAddress(NetworkType.MSOCKET).getAddress(),
                Collector.getAddress(NetworkType.MSOCKET).getPort()
        );
        try {
            multicastSocket.send(packet);
        } catch (IOException ex) {
            ret = false;
        }
        return ret;
    }
}
