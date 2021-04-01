package com.example.springshop.dao;

import com.example.springshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryProductDao extends JpaRepository<Product, Long> {

    //void delete(Long id);

    @Query("select p from  Product p where p.stock > 0 and p.price is not null")
    List<Product> findProducts();

    @Query("select p from Product p where p.id=:id")
    Product getProduct(@Param("id")Long id);
}
