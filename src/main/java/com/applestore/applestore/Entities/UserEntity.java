package com.applestore.applestore.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import org.hibernate.sql.ast.tree.expression.Collation;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "nvarchar(20)" ,nullable = false)
    private String f_name;

    @Column(columnDefinition = "nvarchar(20)" ,nullable = false)
    private String l_name;

    @Column(name="reset_password_token",columnDefinition = "nvarchar(50)")
    private String resetPasswordToken;

    @Column(nullable = false)
    private String gmail;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Collection<Role> roles;
}