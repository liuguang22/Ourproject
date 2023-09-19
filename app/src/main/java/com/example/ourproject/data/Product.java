package com.example.ourproject.data;

import android.graphics.Bitmap;

public class Product {
    private String name;
    private double price;
    private int ImageId;
    private Bitmap bitmap;
    private String describe;

    public Product(String name, double price,int ImageId,Bitmap bitmap,String describe) {
        this.setName(name);
        this.setPrice(price);
        this.setImageId(ImageId);
        this.setBitmap(bitmap);
        this.setDescribe(describe);
    }
    // getter 和 setter 方法

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }



}
