package com.example.springshop.dao;

import com.example.springshop.enums.SortField;
import com.example.springshop.enums.SortType;
import com.example.springshop.exceptions.ProductNotFoundException;
import com.example.springshop.model.Product;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.springshop.utils.VerifyUtil.verifyNotNull;

@Component
public class ArrayListProductDao implements ProductDao {

    private final List<Product> products;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private long maxId;

    public ArrayListProductDao() {
        products = new ArrayList<>();
    }


    public long getMaxId() {
        return maxId;
    }


    public Product getProduct(Long id) {
        verifyNotNull(id);
        readLock.lock();
        try {
            return products.stream()
                    .filter(product -> id.equals(product.getId()))
                    .findAny()
                    .orElseThrow(() -> new ProductNotFoundException(id));
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Product> findProducts(String query, SortField sortField, SortType sortType) {
        readLock.lock();
        try {
            String[] queryWords = (query != null && !query.equals("")) ? query.split("\\s+") : null;
            return products.stream()
                    .filter(product -> product.getStock() > 0)
                    .filter(product -> product.getPrice() != null)
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
    public void save(Product product) {
        verifyNotNull(product);
        writeLock.lock();
        try {
            if (product.getId() != null) {
                updateProducts(product);
            } else {
                addProduct(product);
            }
        } finally {
            writeLock.unlock();
        }
    }


    private void addProduct(Product product) {
        product.setId(maxId++);
        products.add(product);
    }

    private void updateProducts(Product product) {
        products.stream()
                .filter(p -> product.getId().equals(p.getId()))
                .findAny()
                .ifPresentOrElse(
                        val -> products.set(products.indexOf(val), product),
                        () -> products.add(product)
                );
    }

    @Override
    public void delete(Long id) {
        verifyNotNull(id);
        writeLock.lock();
        try {
            products.removeIf(product -> id.equals(product.getId()));
        } finally {
            writeLock.unlock();
        }
    }
}
