package com.hc.network.client.network;

import com.hc.network.client.model.User;

import java.util.HashMap;

/**
 * 信息管理
 */
public class NetworkManage {
    private HashMap<String, User> personMap = new HashMap<>();
    public User getUser(String id, User defaultUser) {
        User ret = personMap.get(id);
        if (ret == null)
            personMap.put(id, defaultUser);
        return personMap.get(id);
    }
    public void registerUser(String id, User user) {
        personMap.put(id, user);
    }
    public void registerUser(User user) {
        registerUser(user.getId(), user);
    }
    public void removeUser(User user) {
        removeUser(user.getId());
    }
    public void removeUser(String id) {
        personMap.remove(id);
    }
}
