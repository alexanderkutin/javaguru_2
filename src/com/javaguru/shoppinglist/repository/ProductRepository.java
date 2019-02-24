package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Optional<Product> getProductByName(String name){
        return database.values()
                .stream()
                .filter(product -> product.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public Product insertProduct(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return Optional.ofNullable(database.get(id));
    }
}
