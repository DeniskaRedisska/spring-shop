package com.example.springshop.exceptions;

public class ProductNotFoundException extends RuntimeException {
    private final Long id;

    public ProductNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
