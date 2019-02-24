package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ObjectValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;

import java.util.Optional;

public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private ProductValidationService obligatoryFieldValidationService;
    private ObjectValidator objectValidator;

    public DefaultProductService(ProductRepository productRepository, ProductValidationService obligatoryFieldValidationService){
        this.productRepository = productRepository;
        this.obligatoryFieldValidationService = obligatoryFieldValidationService;
        this.objectValidator = new ObjectValidator();
    }

    @Override
    public Product findBy(Long id) {
        objectValidator.validate(id);
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        return optionalProduct.get();
    }

    @Override
    public Long create(Product product) {
        obligatoryFieldValidationService.validate(product);
        Product newProduct = productRepository.insertProduct(product);
        return newProduct.getId();
    }
}
