package com.crud.demo.repository;

import com.crud.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring automatically gives you: save(), findById(), findAll(), deleteById()
}
