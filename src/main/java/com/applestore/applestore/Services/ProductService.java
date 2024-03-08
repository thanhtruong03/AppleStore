package com.applestore.applestore.Services;
import java.io.*;

import com.applestore.applestore.DTOs.ProductDto;
import com.applestore.applestore.Entities.Product;

import com.applestore.applestore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductDto convertProductToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProduct_id(product.getProduct_id());
        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setStock(Integer.parseInt(product.getStock()));
        productDto.setPrice(Integer.parseInt(product.getPrice()));
        productDto.setColor(product.getColor());
        productDto.setImg(product.getImg());
        return productDto;
    }

    public Product convertProductDtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setProduct_id(productDto.getProduct_id());
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setStock(String.valueOf(productDto.getStock()));
        product.setPrice(String.valueOf(productDto.getPrice()));
        product.setColor(productDto.getColor());
        product.setImg(productDto.getImg());
        System.out.println(product.getImg());
        return product;
    }

    public String encodingImage(MultipartFile multipartFile){
        String base64Image = null;
        try {
            base64Image = Base64.getEncoder().encodeToString(multipartFile.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
        return base64Image;
    }


    public List<ProductDto> listALlProduct(){
        List<ProductDto> listAllProduct = new ArrayList<>();
        System.out.println("List DTO: ");
        for(Product pro : productRepository.findAll()){
            listAllProduct.add(convertProductToDto(pro));
        }
        for (ProductDto productDto : listAllProduct){
            System.out.println(productDto.getName());
        }
        return listAllProduct;
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public Product getProductById(int id){
        return productRepository.getById(id);
    }

    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }

    public List<ProductDto> findProductByName(String search){
        List<ProductDto> listResult = new ArrayList<>();
        for (Product pro : productRepository.findProductsByNameIgnoreCase(search)){
            listResult.add(convertProductToDto(pro));
        }
        return listResult;
    }

    public List<ProductDto> getAllOrderByPriceAsc(Boolean condition){
        List<ProductDto> listProductOrderByPriceAsc = null;
        if (condition){
            listProductOrderByPriceAsc = listALlProduct().stream().sorted(Comparator.comparingInt(ProductDto::getPrice))
                    .toList();
        } else {
            listProductOrderByPriceAsc = listALlProduct().stream().sorted(Comparator.comparingInt(ProductDto::getPrice).reversed())
                    .collect(Collectors.toList());
        }
        for (ProductDto productDto : listProductOrderByPriceAsc){
            System.out.println(productDto.getPrice());
        }
        return listProductOrderByPriceAsc;
    }

    public List<String> getAllColor(){
        List<String> listColor = productRepository.getAllColor();
        System.out.println("List color: ");
        for (String color : listColor){
            System.out.println(color);
        }
        return listColor;
    }

    public List<ProductDto> getProductByColor(String color){
        List<ProductDto> listProductByColor = new ArrayList<>();
        for (Product product : productRepository.getProductsByColorIgnoreCase(color)){
            listProductByColor.add(convertProductToDto(product));
        }
        return listProductByColor;
    }
}
