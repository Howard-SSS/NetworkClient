package com.hc.network.client.util;

import com.hc.network.client.network.NetworkType;

import java.awt.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.UUID;

/*
 * 个人信息收集器
 */
public class Collector {
    public static String name = "";
    public static InetSocketAddress chatAddress;
    // 接受信息的网络
    private static HashMap<NetworkType, InetSocketAddress> map = new HashMap<>();
    public static String id = UUID.randomUUID().toString();
    public static InetSocketAddress getAddress(NetworkType type) {
        if (map.get(type) == null) {
            switch (type) {
                case MSOCKET:
                    map.put(type, new InetSocketAddress("239.1.2.3", 8000));
                    break;
            }
        }
        return map.get(type);
    }
    public static Font nameFont = new Font("宋体", Font.PLAIN, 12);
}
