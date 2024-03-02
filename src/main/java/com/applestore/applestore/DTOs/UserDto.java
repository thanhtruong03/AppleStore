package com.applestore.applestore.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String gmail;
    private String l_name;
    private String f_name;
    public String toString() {
        return "Username: "+getUsername()+"Password: " + getPassword() + ", gmail: " + getGmail() + ", Lastname: "+getL_name()  + ", Firstname: "+getF_name();
    }
}