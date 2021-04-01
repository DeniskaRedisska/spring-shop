package com.example.springshop.service.impl;

import com.example.springshop.dao.RepositoryProductDao;
import com.example.springshop.enums.SortField;
import com.example.springshop.enums.SortType;
import com.example.springshop.model.Product;
import com.example.springshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DefaultProductService implements ProductService {

    private final RepositoryProductDao productDao;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    @Autowired
    public DefaultProductService(RepositoryProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> findProducts(String query, SortField sortField, SortType sortType) {
        String[] queryWords = (query != null && !query.equals("")) ? query.split("\\s+") : null;
        readLock.lock();
        List<Product> products = productDao.findProducts();
        try {
            return products.stream()
                    .map(product -> Map.entry(product, getNumberOfCollisions(queryWords, product)))
                    .filter(entry -> entry.getValue() > 0)
                    .sorted(getSortComparator(sortField, sortType))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
        } finally {
            readLock.unlock();
        }
    }

    private long getNumberOfCollisions(String[] queryWords, Product product) {
        if (queryWords == null) return 1;
        return Arrays.stream(queryWords).filter(word -> product.getDescription().toLowerCase()
                .contains(word.toLowerCase()))
                .count();
    }

    private Comparator<Map.Entry<Product, Long>> getSortComparator(SortField field, SortType type) {
        if (field == null || type == null) return this::relevanceSort;
        return Comparator.comparing(
                keyExtractor(field),
                getOrderTypeComparator(type)
        );
    }

    private Function<Map.Entry<Product, Long>, Comparable> keyExtractor(SortField field) {
        if (field == SortField.price) return product -> product.getKey().getPrice();
        else return product -> product.getKey().getDescription();
    }


    private Comparator<Comparable> getOrderTypeComparator(SortType type) {
        if (type == SortType.asc) return Comparator.naturalOrder();
        else return Comparator.reverseOrder();
    }

    private int relevanceSort(Map.Entry<Product, Long> p1, Map.Entry<Product, Long> p2) {
        return Long.compare(p2.getValue(), p1.getValue());
    }


    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productDao.getProduct(id);
    }


    @Override
    public void save(Product product) {
        productDao.save(product);
    }

//    @Override
//    public void delete(Long id) {
//        productDao.delete(id);
//    }
}
