package com.applestore.applestore.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD

@Controller
public class UserController {
    @GetMapping("/home")
    public String home(){
        return "home";
=======
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
    @GetMapping("/hi")
    public String hello(){
        return "hello";
>>>>>>> df5d782072a34a4216d27defaeba29faf4c840b7
    }
}
