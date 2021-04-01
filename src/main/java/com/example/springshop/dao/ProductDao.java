package com.example.springshop.dao;

import com.example.springshop.model.Product;
import com.example.springshop.enums.SortField;
import com.example.springshop.enums.SortType;

import java.util.List;


public interface ProductDao {
    Product getProduct(Long id);

    List<Product> findProducts(String query, SortField sortField, SortType sortType);

    void save(Product product);

    void delete(Long id);
}
