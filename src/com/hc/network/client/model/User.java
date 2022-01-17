package com.hc.network.client.model;

public class User {
    String id;
    String name;
    int headNum;
    public User(String id) {
        this.id = id;
        this.name = "Robot";
        this.headNum = 1;
    }
    public User(String id, String name) {
        this(id);
        this.name = name;
        this.headNum = 1;
    }
    public User(String id, String name, int headNum) {
        this(id, name);
        this.headNum = headNum;
    }
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

    /**
     * 以重写
     * @return id
     */
    @Override
    public String toString() {
        return id;
    }
}
