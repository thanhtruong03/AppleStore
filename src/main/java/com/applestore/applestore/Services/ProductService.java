package com.applestore.applestore.Services;

import com.applestore.applestore.Entities.Product;
import com.applestore.applestore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listALlProduct(){
        List<Product> listAll = productRepository.findAll();
        return listAll;
    }

}
