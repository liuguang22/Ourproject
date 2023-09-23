package com.example.ourproject;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.google.gson.JsonArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//public class shop {
////    private String Title;
////    private String Price;
////    private String Content;
////    private int ImageId;
////    private Bitmap bitmap;
////
////    public String getTitle() {
////        return Title;
////    }
////
////    public void setTitle(String title) {
////        this.Title = title;
////    }
////
////
//    public Integer getPrice() {
//        return price;
//    }
//
//    public void setPrice(Integer price) {this.price = price;}
//
//
//
//
////
////    public void setImage(Bitmap bitmap) {this.bitmap=bitmap;}
////    public Bitmap getImage(){return bitmap;}
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAddr() {
//        return addr;
//    }
//
//    public void setAddr(String addr) {
//        this.addr = addr;
//    }
//
//    public  String getAddKey(){
//        return addKey;
//    }
//    public void setAddKey(String addKey){
//        this.addKey=addKey;
//    }
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public String getUser() {
//        return user;
//    }
//
//    public void setUser(String user) {
//        this.user = user;
//    }
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//
//    public String getContentUrl() {
//        return mContentUrl;
//    }
//
//    public void setContentUrl(String mContentUrl) {
//        this.mContentUrl = mContentUrl;
//    }
//
//    public Integer getId() {
//        return Id;
//    }
//
//    public shop() {
//    }
//
//    @Expose(serialize = false, deserialize = false)
//    private Integer Id;
//
//
//    @SerializedName("addr")
//    private String addr;
//    @SerializedName("appIsShare")
//    private Integer appIsShare;
//    @SerializedName("addKey")
//    private String addKey;
//    @SerializedName("avatar")
//    private String avatar;
//    @SerializedName("content")
//    private String name;
//    @SerializedName("price")
//    private Integer price;
//    @SerializedName("createTime")
//    private Integer createTime;
//    @SerializedName("imageCode")
//    private Integer imageCode;
//    @SerializedName("status")
//    private Integer status;
//    @SerializedName("tUserId")
//    private Integer tUserId;
//    @SerializedName("typeId")
//    private Integer typeId;
//    @SerializedName("tuserId")
//    private Integer tuserId;
//
////    @SerializedName("type")
////    private String type;
////    @SerializedName("price")
////    private Integer price;
//
//    @SerializedName("username")
//    private String user;
//    @SerializedName("typeName")
//    private String type;
//
//    @SerializedName("url")
//    private String mContentUrl;
//
//    @SerializedName("imageUrlList")
//    private Array image;
//    @SerializedName("current")
//    private Integer current;
//    @SerializedName("size")
//    private Integer size;
//    @SerializedName("total")
//    private Integer total;
//    @SerializedName("records")
//    private Array records;
//
//}
public class shop {
    private Integer current;
    private List<Record> records;
    private Integer size;
    private Integer total;

    public Integer getCurrent() {
        return current;
    }

    public List<Record> getRecords() {
        return records;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getTotal() {
        return total;
    }
    private String type;
    public String getType(){
        return type;
    }
    public shop(){

    }

    @Override
    public String toString() {
        return "shop{" +
                "current=" + current +
                ", record'" + records  +
                ", size=" + size +
                ", total=" + total +

                '}';

    }

}