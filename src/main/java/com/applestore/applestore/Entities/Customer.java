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
    private int user_id;

    @Column(unique = true, nullable = false)
    private String address_line;

    @Column(unique = true, nullable = false)
    private String country;

    @Column(unique = true, nullable = false)
    private String city;

    @Column(unique = true, nullable = false)
    private String phone;
}
