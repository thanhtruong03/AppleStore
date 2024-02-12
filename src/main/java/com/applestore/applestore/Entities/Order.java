package com.applestore.applestore.Entities;

import jakarta.persistence.*;
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    private int order_id;

    @Column(unique = true, nullable = false)
    private int customer_id;

    @Column(unique = true, nullable = false)
    private int product_id;

    @Column(unique = true, nullable = false)
    private String order_date;
}
