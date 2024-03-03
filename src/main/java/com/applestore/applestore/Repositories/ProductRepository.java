package com.applestore.applestore.Repositories;

import com.applestore.applestore.DTOs.ProductDto;
import com.applestore.applestore.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findProductsByNameIgnoreCase(String search);
}
