package com.cafemanagement.freshcafe.model;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String status;

    public Product(String id, String name, double price, int quantity, String category, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.status = status;
    }
    public void setProduct(Product product){
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        quantity = product.getQuantity();
        category = product.getCategory();
        status = product.getStatus();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
