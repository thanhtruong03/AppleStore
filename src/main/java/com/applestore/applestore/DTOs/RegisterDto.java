package com.applestore.applestore.DTOs;

import com.applestore.applestore.Entities.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String username;
    private String password;
    private String gmail;
    private String l_name;
    private String f_name;
    private List<Role> roles;
}