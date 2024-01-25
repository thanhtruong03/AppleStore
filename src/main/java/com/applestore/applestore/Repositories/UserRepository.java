package com.applestore.applestore.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.applestore.applestore.Entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}