package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.service.validation.product.ProductValidationException;

public class ObjectValidator implements ValidatorInterface {
    @Override
    public void validate(Object object) throws ProductValidationException {
        checkInstantiation(object);
    }
}
