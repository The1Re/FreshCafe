package com.cafemanagement.freshcafe.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private boolean status;

    public Product(String id, String name, double price, int quantity, String category, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return String.format("%s\t%s\t%.2f\t%d\t%s\t%b",
                id,
                name,
                price,
                quantity,
                category,
                status
        );
    }
}
