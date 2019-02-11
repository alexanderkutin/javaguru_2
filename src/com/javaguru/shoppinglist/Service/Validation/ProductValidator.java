package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public interface ProductValidator {

    void validate(Object object) throws ProductValidationException;

    default void checkInstantiation(Object object) throws ProductValidationException {
        if(object == null){
            throw new ProductValidationException("Null object");
        }
    }

    default Product checkProductInstantiation(Object object) throws ProductValidationException {
        if ((object == null) || (Product.class != object.getClass())){
            throw new ProductValidationException("Product object error");
        }
        return (Product)object;
    }
}
