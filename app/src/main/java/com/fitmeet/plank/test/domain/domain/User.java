package com.fitmeet.plank.test.domain.domain;

/**
 * Created by lvhonghe on 16/6/3.
 */
public class User {

    private int id;
    private int wechatid;
    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWechatid() {
        return wechatid;
    }

    public void setWechatid(int wechatid) {
        this.wechatid = wechatid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
