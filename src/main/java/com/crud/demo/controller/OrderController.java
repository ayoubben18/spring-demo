package com.crud.demo.controller;

import com.crud.demo.dto.OrderRequest;
import com.crud.demo.model.Order;
import com.crud.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderRequest request, Principal principal){
        // returns our username from the JwtFilter
        Order order = orderService.placeOrder(request, principal.getName());

        return ResponseEntity.ok(order);
    }
}
