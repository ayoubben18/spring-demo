package com.crud.demo.service;

import com.crud.demo.model.User;
import com.crud.demo.repository.OrderRepository;
import com.crud.demo.repository.ProductRepository;
import com.crud.demo.ResourceNotFoundException;
import com.crud.demo.dto.OrderRequest;
import com.crud.demo.model.Order;
import com.crud.demo.model.Product;
import com.crud.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order placeOrder(OrderRequest request, String username){

        // find the logged in user
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found:"+ username));


        // Create a new order instance
        Order order = new Order();
        order.setUser(user); // associate it to the order
        order.setCustomerEmail(user.getUsername());

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
