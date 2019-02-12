package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Repository.Validation.ProductNotFoundException;
import com.javaguru.shoppinglist.Repository.ProductRepository;
import com.javaguru.shoppinglist.Service.Validation.ObjectValidator;
import com.javaguru.shoppinglist.Service.Validation.ProductValidationException;
import com.javaguru.shoppinglist.Service.Validation.ProductValidationService;

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
    public Product findBy(Long id) throws IllegalArgumentException, ProductNotFoundException {
        objectValidator.validate(id);
        return productRepository.getProductById(id);
    }

    @Override
    public Long create(Product product) throws ProductValidationException, IllegalArgumentException {
        obligatoryFieldValidationService.validate(product);
        Product newProduct = productRepository.insertProduct(product);
        return newProduct.getId();
    }
}
