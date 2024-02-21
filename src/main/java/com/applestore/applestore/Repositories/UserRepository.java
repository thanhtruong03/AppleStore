package com.applestore.applestore.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.applestore.applestore.Entities.User;


public interface UserRepository extends JpaRepository<User, Integer> {
}