package com.hc.network.client.model;

public class User {
    String id;
    String name;
    int headNum;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getHeadNum() {
        return headNum;
    }

    public User setHeadNum(int headNum) {
        this.headNum = headNum;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", headNum=" + headNum +
                '}';
    }
}
