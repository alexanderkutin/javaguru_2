package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.service.validation.product.ObjectValidationException;

public interface ValidatorInterface<T> {

    void validate(T object);

    default void checkInstantiation(Object object) {
        if(object == null){
            throw new ObjectValidationException("Object must be not null");
        }
    }
}
