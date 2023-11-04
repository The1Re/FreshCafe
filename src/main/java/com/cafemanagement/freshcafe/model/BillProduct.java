package com.cafemanagement.freshcafe.model;

import javafx.scene.image.Image;

public class BillProduct {
    private String name;
    private double price;
    private int amount;
    private Image image;

    public BillProduct(String name, double price, int amount, Image image) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public Image getImage() {
        return image;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
