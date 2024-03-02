package com.applestore.applestore.Controllers;


import com.applestore.applestore.DTOs.UserDto;
import com.applestore.applestore.Services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("")
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String loginForm(Model model){
        return "/Fragments/auth/login";
    }

    @PostMapping("/login")
    public String login(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password, Model model, RedirectAttributes redirectAttributes) throws IOException {
        System.out.println("Username: "+ username +", Password: " + password);
        UserDto userDto = userService.getUser(username);
        System.out.println(userDto);
        if(userDto ==null){
            redirectAttributes.addFlashAttribute("message", "Tài khoản không tồn tại , vui lòng kiểm tra lại.");
            return "redirect:/login";
        }
        if(!userDto.getPassword().equals(password)){
            redirectAttributes.addFlashAttribute("message", "Tài khoản hoặc mật khẩu không chính xác, vui lòng kiểm tra lại.");
            return "redirect:/login";
        }

        Cookie cookie = new Cookie("username", userDto.getUsername());
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        response.addCookie(cookie);
        if(userDto.getIs_admin() == 1){
            return "redirect:/login";
        }else if (userDto.getIs_admin() == 0){
            return "redirect:/login";
        }
        return "/Fragments/auth/login";
    }
}
