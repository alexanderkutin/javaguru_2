package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;

public interface ProductService {
    Long create(ProductDTO productDTO);
    ProductDTO findById(Long id);
    Product findProductById(Long id);
}
