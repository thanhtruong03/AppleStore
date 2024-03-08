package com.applestore.applestore.Controllers;

import com.applestore.applestore.DTOs.RegisterDto;
import com.applestore.applestore.DTOs.UserDto;
import com.applestore.applestore.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/Fragments/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "/Fragments/auth/register";
    }
    @GetMapping("/register/admin")
    public String getRegisterForAdminForm(Model model) {
        RegisterDto user = new RegisterDto();
        model.addAttribute("user", user);
        return "/Fragments/auth/register-admin";
    }

    //
    @PostMapping("/register")
    public String register(@ModelAttribute("user") RegisterDto user
            ,BindingResult result, Model model
    ) {
        System.out.println("User"+user);
//        UserDto existingUserEmail = userService.findByGmail(user.getGmail());
//        if (existingUserEmail != null && existingUserEmail.getGmail() != null && !existingUserEmail.getGmail().isEmpty()) {
//            return "redirect:/register?fail";
//        }
//        UserDto existingUserUsername = userService.findByUsername(user.getUsername());
//        if (existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
//            return "redirect:/register?fail";
//        }
//        if (result.hasErrors()) {
//            model.addAttribute("user", user);
//            return "/Fragments/auth/register";
//        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
    @PostMapping("/register/admin")
    public String registerForAdmin(@ModelAttribute("user") RegisterDto user
            ,BindingResult result, Model model
    ){
        System.out.println("User"+user);
        userService.saveAdmin(user);
        return "redirect:/register/admin?success";
    }
}
