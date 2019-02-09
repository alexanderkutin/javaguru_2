package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Service.Product;

public interface ProductService {

    Long create(Product product);

    Product findBy(Long id);

}
