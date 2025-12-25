package com.crud.demo;

import jakarta.validation.constraints.*;

public record ProductRequest (
    @NotBlank(message = "Name is required")
    @Size(min =3, max = 50, message = "Name must be 3-50 characters")
    String name,

    @Min(value = 0, message = "Price cannot be negative")
    double price
){}