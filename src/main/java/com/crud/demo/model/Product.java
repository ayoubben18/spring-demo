package com.crud.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.hibernate.query.Order;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY) // many products can be in one order
    @JoinColumn(name = "order_id") // this creates the foreign key column
    @JsonIgnore // prevents infinite loops when converting to json
    private Order order;
}
