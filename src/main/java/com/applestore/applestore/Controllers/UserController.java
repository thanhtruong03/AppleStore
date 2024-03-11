package com.applestore.applestore.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.applestore.applestore.DTOs.CustomerDto;
import com.applestore.applestore.DTOs.OrderDto;
import com.applestore.applestore.DTOs.ProductDto;
import com.applestore.applestore.DTOs.detailOrderDto;
import com.applestore.applestore.Entities.Customer;
import com.applestore.applestore.Entities.Order;
import com.applestore.applestore.Entities.Product;
import com.applestore.applestore.Repositories.ProductRepository;
import com.applestore.applestore.Security.CustomUserDetails;
import com.applestore.applestore.Services.CustomerService;
import com.applestore.applestore.Services.OrderService;
import com.applestore.applestore.Services.ProductService;


import lombok.Data;

import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService ;
	
	
//	@GetMapping("/")
//    public String index(Model model, Authentication authentication){
//        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
//        // Lấy tên người dùng
//        model.addAttribute("user_id",u.getUser_id());
//        return "/Fragments/user/header";
//    }
	
	
    @GetMapping("/customer_info")
    public String CustomerInfo(Model model,Authentication authentication) {
    	
    	CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        
    	CustomerDto customerDto = new CustomerDto();
		customerDto = customerService.getCustomerByuserId(customUserDetails.getUser_id());
		model.addAttribute("customerDto", customerDto);
    	model.addAttribute("user", customUserDetails);
    	
        return "/Fragments/user/customer_info";
    }
    
    @GetMapping("/edit")
    public String Edit(Model model,Authentication authentication) {
    	CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        
    	CustomerDto customerDto = new CustomerDto();
		customerDto = customerService.getCustomerByuserId(customUserDetails.getUser_id());
		model.addAttribute("customerDto", customerDto);
    	
        return "/Fragments/user/Edit";
    }
    
    @PostMapping("/update")
    public String update(
            @RequestParam("address_line") String address_line,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("phone") String phone,
            Authentication authentication,
            Model model
    ){  
        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
        
        CustomerDto customerDto = customerService.getCustomerByuserId(u.getUser_id());
        Customer customer = customerService.getCustomerById1(customerDto.getCustomer_id());
        customer.setAddress_line(address_line);
        customer.setCountry(country);
        customer.setCity(city);
        customer.setPhone(phone);
        customerService.saveCustomer(customer);
        
        
        model.addAttribute("customerDto", customer);
        model.addAttribute("user", u);
        
        return "/Fragments/user/customer_info";
    }
    
    
	
    
    @GetMapping("/enter_customer_info")
    public String EnterCustomerInfo() {
        return "/Fragments/user/enter_customer_info";
    }
    
    @PostMapping("/save")
    public String save(
            @RequestParam("address_line") String address_line,
            @RequestParam("country") String country,
            @RequestParam("city") String city,
            @RequestParam("phone") String phone,
            Authentication authentication,
            Model model
    ){  
        CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUser_id(u.getUser_id());
        customerDto.setAddress_line(address_line);
        customerDto.setCountry(country);
        customerDto.setCity(city);
        customerDto.setPhone(phone);
    
        Customer customer = customerService.convertCustomerDtoToCustomer(customerDto);
        customerService.saveCustomer(customer);
        
        
        model.addAttribute("customerDto", customerDto);
        
        
        return "/Fragments/user/customer_info";
    }

	
    @GetMapping("products")
    public String products(Model model){
    	List<ProductDto> listAllProduct = new ArrayList<>();
    	listAllProduct = productService.listALlProduct();
    	model.addAttribute("products", listAllProduct);
    			
    	return "/Fragments/user/products";
    }
    
    
    @GetMapping("/product_detail")
    public String product_detail(@RequestParam("product_id") int productId,
    		Authentication authentication,
    		Model model){
    	
    	CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
    	CustomerDto customerDto = new CustomerDto();
    	customerDto = customerService.getCustomerByuserId(u.getUser_id());
    	
        Product product = productService.getProductById(productId);
        ProductDto productDto = productService.convertProductToDto(product);
        
        model.addAttribute("productDto", productDto);
        model.addAttribute("customerDto", customerDto);

        return "/Fragments/user/Product_detail";
    }
    
    

    
    @GetMapping("/checkout")
    public String checkout(
    		@RequestParam("product_id") int product_id,
            Authentication authentication,
            Model model
    		
    ) {	
    	CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
		CustomerDto customerDto = new CustomerDto();
		customerDto = customerService.getCustomerByuserId(u.getUser_id());	
		
		LocalDate currentDate = LocalDate.now();
	    String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	   
	    
		OrderDto orderDto = new OrderDto();
		orderDto.setCustomer_id(customerDto.getCustomer_id());
		orderDto.setProduct_id(product_id);
		orderDto.setOrder_date(formattedDate);
		orderDto.setStatus(0);
		Order order = orderService.convertDtoToEntity(orderDto);
		orderService.saveOrder(order);
		
		Product product = productService.getProductById(product_id);     
        String tmp = product.getStock();
        int tmp1 = Integer.parseInt(tmp);
		product.setStock(String.valueOf(tmp1-1));
		productService.saveProduct(product);
		
		
		
        return "/Fragments/user/checkout";
    }
    
    @GetMapping("/purchase_history")
    public String purchaseHistory(
    		Authentication authentication,
    		Model model){
    	CustomUserDetails u = (CustomUserDetails) authentication.getPrincipal();
		CustomerDto customerDto = new CustomerDto();
		customerDto = customerService.getCustomerByuserId(u.getUser_id());	
		
		List<detailOrderDto> listOrder1 = new ArrayList<>();
		listOrder1 = orderService.getDetailOrderByCustomerId(customerDto.getCustomer_id());
		
		
        model.addAttribute("listDetailOrder",listOrder1 );
        
        return "/Fragments/user/purchase_history";
    }
    
    
      

 
    
    

}
