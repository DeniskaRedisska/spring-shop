package com.example.springshop.listener;

import com.example.springshop.model.PriceInfo;
import com.example.springshop.model.Product;
import com.example.springshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Currency;

@Component
public class ContextListener {

    private final ProductService service;

    @Autowired
    public ContextListener(ProductService service) {
        this.service = service;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onContextStart() {
        //saveProducts();
        System.out.println("hi bich");
        //setPriceHistory();
    }

    private void saveProducts() {
        Currency usd = Currency.getInstance("USD");
        service.save(new Product("sgs", "Samsung Galaxy S", new BigDecimal(100), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        service.save(new Product("sgs2", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"));
        service.save(new Product("sgs3", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg"));
        service.save(new Product("iphone", "Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"));
        service.save(new Product("iphone6", "Apple iPhone 6", new BigDecimal(1000), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg"));
        service.save(new Product("htces4g", "HTC EVO Shift 4G", new BigDecimal(320), usd, 3, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/HTC/HTC%20EVO%20Shift%204G.jpg"));
        service.save(new Product("sec901", "Sony Ericsson C901", new BigDecimal(420), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg"));
        service.save(new Product("xperiaxz", "Sony Xperia XZ", new BigDecimal(120), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Xperia%20XZ.jpg"));
        service.save(new Product("nokia3310", "Nokia 3310", new BigDecimal(70), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Nokia/Nokia%203310.jpg"));
        service.save(new Product("palmp", "Palm Pixi", new BigDecimal(170), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg"));
        service.save(new Product("simc56", "Siemens C56", new BigDecimal(70), usd, 20, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C56.jpg"));
        service.save(new Product("simc61", "Siemens C61", new BigDecimal(80), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C61.jpg"));
        service.save(new Product("simsxg75", "Siemens SXG75", new BigDecimal(150), usd, 40, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg"));
    }

    private void setPriceHistory() {
        Product product1 = service.getProduct(45L);
        product1.setPriceHistory(new ArrayList<>() {{
            add(new PriceInfo(LocalDateTime.of(2020, Month.JUNE, 12, 0, 0).toLocalDate(), new BigDecimal(80), product1));
            add(new PriceInfo(LocalDateTime.of(2020, Month.AUGUST, 9, 0, 0).toLocalDate(), new BigDecimal(90), product1));
            add(new PriceInfo(LocalDateTime.of(2021, Month.FEBRUARY, 2, 0, 0).toLocalDate(), new BigDecimal(100), product1));
        }});
        product1.setDescription("Samsung Galaxy SSS");
        Product product2 = service.getProduct(46L);
        product2.setPriceHistory(new ArrayList<>() {{
            add(new PriceInfo(LocalDateTime.of(2020, Month.JUNE, 12, 0, 0).toLocalDate(), new BigDecimal(250), product2));
            add(new PriceInfo(LocalDateTime.of(2020, Month.AUGUST, 9, 0, 0).toLocalDate(), new BigDecimal(280), product2));
            add(new PriceInfo(LocalDateTime.of(2021, Month.FEBRUARY, 2, 0, 0).toLocalDate(), new BigDecimal(300), product2));
        }});
        Product product3 = service.getProduct(47L);
        product3.setPriceHistory(new ArrayList<>() {{
            add(new PriceInfo(LocalDateTime.of(2020, Month.JUNE, 12, 0, 0).toLocalDate(), new BigDecimal(100), product3));
            add(new PriceInfo(LocalDateTime.of(2020, Month.AUGUST, 9, 0, 0).toLocalDate(), new BigDecimal(150), product3));
            add(new PriceInfo(LocalDateTime.of(2021, Month.FEBRUARY, 2, 0, 0).toLocalDate(), new BigDecimal(200), product3));
        }});
        Product product4 = service.getProduct(48L);
        product4.setPriceHistory(new ArrayList<>() {{
            add(new PriceInfo(LocalDateTime.of(2020, Month.JUNE, 12, 0, 0).toLocalDate(), new BigDecimal(1500), product4));
            add(new PriceInfo(LocalDateTime.of(2020, Month.AUGUST, 9, 0, 0).toLocalDate(), new BigDecimal(1200), product4));
            add(new PriceInfo(LocalDateTime.of(2021, Month.FEBRUARY, 2, 0, 0).toLocalDate(), new BigDecimal(1000), product4));
        }});
        Product product5 = service.getProduct(49L);
        product5.setPriceHistory(new ArrayList<>() {{
            add(new PriceInfo(LocalDateTime.of(2020, Month.JUNE, 12, 0, 0).toLocalDate(), new BigDecimal(400), product5));
            add(new PriceInfo(LocalDateTime.of(2020, Month.AUGUST, 9, 0, 0).toLocalDate(), new BigDecimal(350), product5));
            add(new PriceInfo(LocalDateTime.of(2021, Month.FEBRUARY, 2, 0, 0).toLocalDate(), new BigDecimal(320), product5));
        }});
        service.save(product1);
        service.save(product2);
        service.save(product3);
        service.save(product4);
        service.save(product5);
    }
}
