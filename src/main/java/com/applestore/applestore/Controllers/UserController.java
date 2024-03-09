package com.applestore.applestore.Controllers;

import com.applestore.applestore.Security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
        // Lấy tên người dùng
        model.addAttribute("user_id",u.getUser_id());
        return "/Fragments/user/header";
    }
}
