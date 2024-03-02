package com.applestore.applestore.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String f_name;

    @Column(nullable = false)
    private String l_name;

    @Column(nullable = false)
    private String gmail;

    @Column(nullable = false)
    private int isAdmin;
}