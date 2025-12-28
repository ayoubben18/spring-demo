package com.crud.demo.service;

import com.crud.demo.repository.OrderRepository;
import com.crud.demo.repository.ProductRepository;
import com.crud.demo.ResourceNotFoundException;
import com.crud.demo.dto.OrderRequest;
import com.crud.demo.model.Order;
import com.crud.demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order placeOrder(OrderRequest request){
        // Create a new order instance
        Order order = new Order();
        order.setCustomerEmail(request.customerEmail());

        // find all requested products
        List<Product> products = productRepository.findAllById(request.productIds());

        if(products.isEmpty()){
            throw new ResourceNotFoundException("No valid products found for these products");
        }

        for (Product product : products){
            order.addProduct(product);
        }

        return orderRepository.save(order);
    }
}
