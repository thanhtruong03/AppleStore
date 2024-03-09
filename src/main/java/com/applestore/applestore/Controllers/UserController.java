package com.applestore.applestore.Controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.applestore.applestore.DTOs.ProductDto;
import com.applestore.applestore.Entities.Product;
import com.applestore.applestore.Repositories.ProductRepository;
import com.applestore.applestore.Security.CustomUserDetails;
import com.applestore.applestore.Services.ProductService;

import lombok.Data;

import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private ProductService productService;
	
//	@GetMapping("/")
//    public String index(Model model, Authentication authentication){
//        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
//        // Lấy tên người dùng
//        model.addAttribute("user_id",u.getUser_id());
//        return "/Fragments/user/header";
//    }


	
    @GetMapping("products")
    public String products(Model model){
    	List<ProductDto> listAllProduct = new ArrayList<>();
    	listAllProduct = productService.listALlProduct();
    	model.addAttribute("products", listAllProduct);
    			
    	return "/Fragments/user/products";
    }
    
    
    @GetMapping("/product_detail")
    public String product_detail(@RequestParam("product_id") int productId, Model model){
       
        Product product = productService.getProductById(productId);
        ProductDto productDto = productService.convertProductToDto(product);

        model.addAttribute("productDto", productDto);

        return "/Fragments/user/Product_detail";
    }
    

 
    
    

}
