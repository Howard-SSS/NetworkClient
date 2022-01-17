package com.hc.network.client.network;

import com.hc.network.client.model.MessageAbstract;
import com.hc.network.client.model.User;
import com.hc.network.client.model.content.LoginModel;
import com.hc.network.client.model.content.QuitModel;
import com.hc.network.client.model.content.TextModel;
import com.hc.network.client.util.Collector;
import com.hc.network.client.util.Translate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/*
 * 网络收发器
 */
public abstract class NetworkPost implements AcceptImpl {
    protected NetworkManage manage = new NetworkManage();
    private MulticastSocket multicastSocket;
    public NetworkPost() {
        try {
            multicastSocket = new MulticastSocket(Collector.getAddress(NetworkType.MSOCKET).getPort());
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
    public void setUser(String id, User user) {
        manage.setUser(id, user);
    }
    public void setUser(User user) {
        manage.setUser(user);
    }
}
