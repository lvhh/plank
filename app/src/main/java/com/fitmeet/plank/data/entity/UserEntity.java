package com.fitmeet.plank.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lvhonghe on 16/6/6.
 */
public class UserEntity {

    @SerializedName("id")
    private int id;

    @SerializedName("wechatid")
    private int wecharid;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWecharid() {
        return wecharid;
    }

    public void setWecharid(int wecharid) {
        this.wecharid = wecharid;
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

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", wecharid=" + wecharid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
