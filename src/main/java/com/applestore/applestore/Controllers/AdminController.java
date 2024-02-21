package com.applestore.applestore.Controllers;

import com.applestore.applestore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;

    @GetMapping("/viewAll")
    public String viewAllProduct(Model model){
        model.addAttribute("listAllProduct", productService.listALlProduct());
        return "Fragments/admin/viewAllProduct";
    }
    @GetMapping("admin/edit/{id}")
    public String editInformation(){
        return "editInforProduct";
    }

}
