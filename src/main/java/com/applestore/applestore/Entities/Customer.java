package com.applestore.applestore.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    private int customer_id;

    @Column(unique = true, nullable = false)
    private String user_id;

    @Column(unique = true, nullable = false)
    private String address_line;

    @Column(unique = true, nullable = false)
    private String country;

    @Column(unique = true, nullable = false)
    private String city;

    @Column(unique = true, nullable = false)
    private String phone;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddress_line() {
        return address_line;
    }

    public void setAddress_line(String address_line) {
        this.address_line = address_line;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer() {
    }

    public Customer(int customer_id, String user_id, String address_line, String country, String city, String phone) {
        this.customer_id = customer_id;
        this.user_id = user_id;
        this.address_line = address_line;
        this.country = country;
        this.city = city;
        this.phone = phone;
    }
}