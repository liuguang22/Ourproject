package com.example.ourproject.home;

import java.util.ArrayList;

public class Record {
    private String addr;
    private Integer appIsShare;
    private String appKey;
    private String avatar;
    private String content;
    private long createTime;
    private long id;
    private long imageCode;
    private ArrayList<String> imageUrlList;
    private float price;
    private int status;
    private long tUserId;
    private long tuserId;
    private int typeId;
    private String typeName;
    private String username;

    public String getAddr() {
        return addr;
    }
    public void setContent(String content){
        this.content=content;
    }

    public Integer getAppIsShare() {
        return appIsShare;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getContent() {
        return content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getId() {
        return id;
    }

    public long getImageCode() {
        return imageCode;
    }

    public ArrayList<String> getImageUrlList() {
        return imageUrlList;
    }
//    public void setImageUrlList(ArrayList<String> ImageUrlList) {
//        this.imageUrlList=imageUrlList;
//    }

    public float getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public long getTUserId() {
        return tUserId;
    }

    public long getTuserId() {
        return tuserId;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getUsername() {
        return username;
    }


    @Override
    public String toString(){
        return "Record{" +
                "addr=" + addr  +
                ", appIsShare='" + appIsShare  +
                ", appKey=" + appKey +
                ", avatar=" + avatar +
                ", content" + content  +
                ", createTime='" + createTime  +
                ", id=" + id +
                ", imageCode=" + imageCode  +
                ", imageUrlList" + imageUrlList  +
                ", price='" + price  +
                ", status=" + status +
                ", tUserId=" + tUserId +
                ", tuserId" + tuserId  +
                ", typeId='" + typeId  +
                ", typeName=" + typeName +
                ", username=" + username +
                '}';
    }

}


