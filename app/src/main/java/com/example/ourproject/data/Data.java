package com.example.ourproject.data;

import java.util.List;

public class Data {

    private int imageCode;
    private String addr;
    private String avatar;
    private String content;
    private int createTime;
    private long id;
    private int price;
    private int status;
    private int tUserId;
    private int tuserId;
    private int typeId;
    private String typeName;
    private String username;
    private List<String> imageUrlList;
    private String appKey;
    private int money;
    private String password;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public String getAppKey() {
        return appKey;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public int getMoney() {
        return money;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setImageCode(int imageCode) {
        this.imageCode = imageCode;
    }
    public int getImageCode() {
        return imageCode;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
    public List<String> getImageUrlList() {
        return imageUrlList;
    }


    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getAddr() {
        return addr;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
    public int getCreateTime() {
        return createTime;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setTUserId(int tUserId) {
        this.tUserId = tUserId;
    }
    public int getTUserId() {
        return tUserId;
    }

    public void setTuserId(int tuserId) {
        this.tuserId = tuserId;
    }
    public int getTuserId() {
        return tuserId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    public int getTypeId() {
        return typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getTypeName() {
        return typeName;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}



