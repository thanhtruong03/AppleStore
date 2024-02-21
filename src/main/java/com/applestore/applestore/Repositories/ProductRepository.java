package com.applestore.applestore.Repositories;

import com.applestore.applestore.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
