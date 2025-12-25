package com.crud.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service// [1] Marks this as a "Bean" that Spring manages
@RequiredArgsConstructor // [2] Automatically creates a constructor for DI
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true) // [3] Optimizes DB performance for reading
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with ID "+ id + " not found."));
    }

    @Transactional // [4] Ensures this operation is "Atomic" (all or nothing)
    public Product save(ProductRequest request){

        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());

        // [3] BUSINESS LOGIC: You can add logic here before saving
        // Example: If price > 1000, mark as "Premium" (if you had that field)

        return productRepository.save(product);
    }
}
