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

    @Column(nullable = true)
    private int is_admin;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(int isAdmin) {
        this.is_admin = isAdmin;
    }

    public User(){}

    public User(int user_id, String username, String password, String f_name, String l_name, String gmail, int is_admin) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.f_name = f_name;
        this.l_name = l_name;
        this.gmail = gmail;
        this.is_admin = is_admin;
    }
}