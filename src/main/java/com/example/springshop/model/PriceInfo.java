package com.example.springshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "price_info")
public class PriceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDate date;
    private BigDecimal price;

    public PriceInfo(LocalDate date, BigDecimal price, Product product) {
        this.date = date;
        this.price = price;
        this.product = product;
    }
}

