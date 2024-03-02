package com.applestore.applestore.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int user_id;
    private String username;
    private String password;
    private String gmail;
    private String l_name;
    private String f_name;
    private int is_admin;




    public String toString() {
        return "Username: "+getUsername()+"Password: " + getPassword() + ", gmail: " + getGmail() + ", Lastname: "+getL_name()  + ", Firstname: "+getF_name();
    }
}