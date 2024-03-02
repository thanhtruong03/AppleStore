package com.applestore.applestore.Services;

import com.applestore.applestore.DTO.UserDto;
import com.applestore.applestore.Entities.User;
import com.applestore.applestore.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public UserDto getUser(String username){
        User user = userRepo.findByUsername(username);
        if(user !=null){
            return mapToUserDto(user);
        }
        return null;
    }
    public List<UserDto> getAllUser(){
        List<User> users = userRepo.findAll();
        if(users !=null){
            return users.stream().map(user -> mapToUserDto(user)).collect(Collectors.toList());
        }
        return null;
    }
    public UserDto mapToUserDto(User user){
        UserDto userDto = UserDto.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .password(user.getPassword())
                .l_name(user.getL_name())
                .f_name(user.getF_name())
                .gmail(user.getGmail())
                .is_admin(user.getIs_admin())
                .build();
        return userDto;
    }

}