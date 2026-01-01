package com.crud.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String customerEmail;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    // keeps both sides of the rship in sync
    public  void addProduct(Product product){
        products.add(product);
        product.setOrder(this);
    }
}
