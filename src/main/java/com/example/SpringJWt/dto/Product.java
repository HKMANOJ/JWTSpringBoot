package com.example.SpringJWt.dto;

import lombok.Builder;

@Builder
public class Product {

    private int productId;
    private String name;

    private int qty;

    private double price;

    public Product() {

    }

    public Product(int productId, String name, int qty, double price) {
        this.productId = productId;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}
