package com.applestore.applestore.Entities;

import jakarta.persistence.*;
@Entity
@Table(name = "Products")
public class Product {

    @Id
    private int product_id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String description;

    @Column(unique = true, nullable = false)
    private String stock;

    @Column(unique = true, nullable = false)
    private String price;

//    @Column(unique = true, nullable = false)
//    private String url;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }

    public Product() {
    }

    public Product(int product_id, String name, String description, String stock, String price, String url) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        //this.url = url;
    }
}
