package com.applestore.applestore.Repositories;

import com.applestore.applestore.Entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
//    User findUserByEmail(String email);

}