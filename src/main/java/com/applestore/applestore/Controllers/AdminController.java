package com.applestore.applestore.Controllers;
import com.applestore.applestore.DTOs.*;

import com.applestore.applestore.Entities.Product;
import com.applestore.applestore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @GetMapping("viewAll")
    public String viewAllProduct(Model model, @Param("search") String search, @Param("selectedItem") String selectedItem){
        List<ProductDto> listAllProduct = new ArrayList<>();
        if (search == null) {
            listAllProduct = productService.listALlProduct();
        }
        else if (search != null){
            listAllProduct = productService.findProductByName(search);
        }
        model.addAttribute("listAllProduct", listAllProduct);
        return "Fragments/admin/viewAllProduct";
    }

    @GetMapping("addNew")
    public String addNewProduct(){
        return "Fragments/admin/addNewProduct";
    }

    @PostMapping("save")
    public String saveNewProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") String price,
            @RequestParam("stock") String stock,
            @RequestParam("img") MultipartFile imageFile
    ){
        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setStock(Integer.parseInt(stock));
        productDto.setPrice(Integer.parseInt(price));
        productDto.setImg(productService.encodingImage(imageFile));
        Product product = productService.convertProductDtoToEntity(productDto);
        productService.saveProduct(product);
        return "redirect:/admin/viewAll";
    }

    @PostMapping("update")
    public String updateProduct(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") String price,
            @RequestParam("stock") String stock
    ){
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setStock(String.valueOf(stock));
        product.setPrice(String.valueOf(price));
        productService.saveProduct(product);
        return "redirect:/admin/viewAll";
    }

    @GetMapping("edit/id={id}")
    public String editInformation(@PathVariable("id") Integer id, Model model){
        model.addAttribute("product", productService.convertProductToDto(productService.getProductById(id)));
        model.addAttribute("id", id);
        return "Fragments/admin/editInforProduct";
    }

    @GetMapping("delete/id={id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/viewAll";
    }
  
    @PostMapping(value = "safe")
    public String xapsep(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/viewAll";
    }


}
