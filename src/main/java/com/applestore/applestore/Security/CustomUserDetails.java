package com.applestore.applestore.Security;

import com.applestore.applestore.Entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomUserDetails implements UserDetails {
    private int user_id;
    private String username;
    private String password;
    private String gmail;
    private String l_name;
    private String f_name;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.gmail = user.getGmail();
        this.l_name = user.getL_name();
        this.f_name = user.getF_name();

        this. authorities = Arrays
                .stream(user.getRoles().toString().split(","))
                        .map(au -> {
                            System.out.println("AU in CUD: "+au);
                            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(au);
                            return sga;
                        })
                        .collect(Collectors.toList());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

//    public static CustomUserDetails mapUserToUserDetail(UserEntity user){
//        List<GrantedAuthority> listAuthorities = user.getRoles().stream()
//                .map(role -> {
//                    System.out.println(role.getName());
//                    return new  SimpleGrantedAuthority(role.getName());
//                })
//                .collect(Collectors.toList());
//        return new CustomUserDetails(
//                user.getUser_id(),
//                user.getUsername(),
//                user.getPassword(),
//                user.getGmail(),
//                user.getL_name(),
//                user.getF_name(),
//                listAuthorities
//        );
//    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}