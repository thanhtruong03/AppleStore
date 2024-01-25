package com.applestore.applestore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.applestore.applestore.Entities.User;
import com.applestore.applestore.Repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}