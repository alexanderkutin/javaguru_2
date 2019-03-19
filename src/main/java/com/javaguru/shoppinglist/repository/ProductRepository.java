package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findProductByName(String name);
    Product insertProduct(Product product);
    Optional<Product> findProductById(Long id);
}
