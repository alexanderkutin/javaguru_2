package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Repository.ProductNotFoundException;
import com.javaguru.shoppinglist.Repository.ProductRepository;
import com.javaguru.shoppinglist.Service.Product;
import com.javaguru.shoppinglist.Service.ProductService;
import com.javaguru.shoppinglist.Service.Validation.ProductValidationException;
import com.javaguru.shoppinglist.Service.Validation.ProductValidationService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private ProductValidationService obligatoryFieldValidationService;

    public DefaultProductService(ProductRepository productRepository, ProductValidationService obligatoryFieldValidationService){
        this.productRepository = productRepository;
        this.obligatoryFieldValidationService = obligatoryFieldValidationService;
    }


    @Override
    public Product findBy(Long id) throws IllegalArgumentException, ProductNotFoundException {

        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        return productRepository.getProductById(id);
    }

    @Override
    public Long create(Product product) throws ProductValidationException {

        obligatoryFieldValidationService.validate(product);
        Product newProduct = productRepository.insertProduct(product);
        return newProduct.getId();
    }

}
