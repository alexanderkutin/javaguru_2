package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public interface ProductCoreValidator {

    void validate(Product product) throws ProductValidationException;

    default void checkInstantiation(Product product) throws ProductValidationException {
        if(product == null){
            throw new ProductValidationException("Product object is null");
        }
    }

}
