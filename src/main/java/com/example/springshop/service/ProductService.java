package com.example.springshop.service;

import com.example.springshop.enums.SortField;
import com.example.springshop.enums.SortType;
import com.example.springshop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProducts(String query, SortField sortField, SortType sortType);

    List<Product> findAll();

    Product getProduct(Long id);

    void save(Product product);

//    void delete(Long id);

}
