package com.example.springshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

@Data
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Code must be not empty")
    @Size(min = 2, max = 10, message = "Code length must be between 2 and 10")
    private String code;

    @NotEmpty(message = "description must be not empty")
    @Size(min = 2,max = 100,message = "Description length must be between 2 and 100" )
    private String description;
    /**
     * null means there is no price because the product is outdated or new
     */
    @Positive(message = "Price must be positive")
    @Min(value = 0,message = "Price must be above zero")
    private BigDecimal price;
    /**
     * can be null if the price is null
     */
    private Currency currency;
    @Positive(message = "Stock must be positive")
    @Min(value = 0,message = "Stock must be above zero")
    private int stock;
    @NotEmpty(message = "Image url must be not empty")
    private String imageUrl;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PriceInfo> priceHistory;

    public Product() {
    }

    public Product(Long id, String code, String description, BigDecimal price, Currency currency, int stock, String imageUrl) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }

    public Product(String code, String description, BigDecimal price, Currency currency, int stock, String imageUrl) {
        this.code = code;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.imageUrl = imageUrl;
    }
}
