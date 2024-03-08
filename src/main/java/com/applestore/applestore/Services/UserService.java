package com.applestore.applestore.Services;

import com.applestore.applestore.DTOs.RegisterDto;
import com.applestore.applestore.DTOs.UserDto;
import com.applestore.applestore.Entities.Role;
import com.applestore.applestore.Entities.UserEntity;
import com.applestore.applestore.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class UserService {
    private final UserRepository userRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepo, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

//    public UserDto findByUsername(String username){
//        UserEntity user = userRepo.findByUsername(username);
//        if(user !=null){
//            return mapToUserDto(user);
//        }
//        return null;
//    }

    public UserDto findByGmail(String email) {
        UserEntity user = userRepo.findUserByGmail(email);
        if(user !=null){
            return mapToUserDto(user);
        }
        return null;
    }
    public void saveUser(RegisterDto registerDto){

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setL_name(registerDto.getL_name());
        user.setF_name(registerDto.getF_name());
        user.setGmail(registerDto.getGmail());
        Role userRole = roleService.findByName("ROLE_USER").orElseThrow(()-> new RuntimeException("Role Is Not Found"));
        Collection<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);
    }
    public void saveAdmin(RegisterDto registerDto){

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setL_name(registerDto.getL_name());
        user.setF_name(registerDto.getF_name());
        user.setGmail(registerDto.getGmail());
        Role userRole = roleService.findByName("ROLE_ADMIN").orElseThrow(()-> new RuntimeException("Role Is Not Found"));
        Collection<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);
    }
    public UserDto mapToUserDto(UserEntity user){
        return UserDto.builder()
                .user_id(user.getUser_id())
                .username(user.getUsername())
                .password(user.getPassword())
                .l_name(user.getL_name())
                .f_name(user.getF_name())
                .gmail(user.getGmail())
                .build();
    }

}