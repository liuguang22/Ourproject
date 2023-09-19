package com.example.ourproject.data;

import java.util.List;

public class Order {
    private List<Product> products;
    private double totalPrice;

    public Order(List<Product> products) {
        this.products = products;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
    }

    // getter 方法
}
