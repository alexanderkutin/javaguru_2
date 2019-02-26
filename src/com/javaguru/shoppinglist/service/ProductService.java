package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

public interface ProductService {
    Long create(Product product);
    Product findBy(Long id);
}
