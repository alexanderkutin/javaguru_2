package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {
    private Set<ProductCoreValidator> productValidatorSet = new HashSet<>();

    public ProductValidationService(){

    }

    public void addValidator(ProductCoreValidator newProductValidator){
        productValidatorSet.add(newProductValidator);
    }

    public void validate(Product product) throws ProductValidationException {

        productValidatorSet.forEach(s -> s.validate(product));
    }
}
