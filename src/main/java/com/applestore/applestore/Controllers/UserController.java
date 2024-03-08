package com.applestore.applestore.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String index(){
        return "/Fragments/user/header";
    }
}
