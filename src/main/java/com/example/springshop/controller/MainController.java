package com.example.springshop.controller;

import com.example.springshop.enums.SortField;
import com.example.springshop.enums.SortType;
import com.example.springshop.model.Product;
import com.example.springshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Currency;


@Controller
public class MainController {

    private ProductService service;

    @Autowired
    public void setService(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public ModelAndView getProducts(@RequestParam(value = "query", required = false, defaultValue = "") String query,
                                    @RequestParam(value = "sort", required = false) SortField sortField,
                                    @RequestParam(value = "order", required = false) SortType sortType) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", service.findProducts(query,
                sortField, sortType));
        modelAndView.setViewName("productList");
        return modelAndView;
    }

    @GetMapping("/products/add")
    public ModelAndView editForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addProduct");
        return modelAndView;
    }

    @PostMapping("/products/add")
    public ModelAndView addProduct(@ModelAttribute @Valid Product product, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("product", product);
            modelAndView.setViewName("addProduct");
            return modelAndView;
        }
        product.setCurrency(Currency.getInstance("USD"));
        service.save(product);
        modelAndView.setViewName("redirect:/products");
        return modelAndView;
    }

    @GetMapping("/products/{id}")
    public ModelAndView getProduct(@PathVariable("id") Long id){
        Product product = service.getProduct(id);
        ModelAndView modelAndView=new ModelAndView("productDetails");
        modelAndView.addObject("product",product);
        return modelAndView;
    }

}
