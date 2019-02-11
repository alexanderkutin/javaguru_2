package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public interface ProductValidatorInterface {

    void validate(Product product) throws ProductValidationException;

    default void checkInstantiation(Object object) throws ProductValidationException {
        if(object == null){
            throw new ProductValidationException("Null object");
        }
    }

}
