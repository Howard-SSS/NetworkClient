package com.hc.network.client.util;

import com.hc.network.client.model.User;
import com.hc.network.client.network.NetworkType;

import java.awt.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.UUID;

/*
 * 个人信息收集器
 */
public class Collector {
    public static int defaultHeadNum = 1;
    public static int userHeadNum = defaultHeadNum;
    public static String defaultName = "Robot";
    public static String name = defaultName;
    public static int chatViewWidth = 437;
    // 接受信息的网络
    private static HashMap<NetworkType, InetSocketAddress> map = new HashMap<>();
    public static String userId = UUID.randomUUID().toString();
    public static InetSocketAddress getAddress(NetworkType type) {
        if (map.get(type) == null) {
            switch (type) {
                case MSOCKET:
                    map.put(type, new InetSocketAddress("239.1.2.4", 8080));
                    break;
            }
        }
        return map.get(type);
    }
    public static Font nameFont = new Font("黑体", Font.PLAIN, 12);
    public static Font textFont = new Font("黑体", Font.PLAIN, 18);
    public static Color nameColor = Color.darkGray;
    public static Color textColor = Color.black;
    public static User getCustomUser() {
        return new User(userId, name, userHeadNum);
    }
}
