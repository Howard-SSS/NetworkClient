package com.hc.network.client.network;

import com.hc.network.client.model.User;

import java.util.HashMap;

/*
 * 信息管理
 */
public class NetworkManage {
    private HashMap<String, User> personMap = new HashMap<>();
    public User getUser(String id) {
        return personMap.get(id);
    }
    public void setUser(String id, User user) {
        personMap.put(id, user);
    }
}
