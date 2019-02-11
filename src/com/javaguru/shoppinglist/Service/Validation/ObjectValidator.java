package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public class ObjectValidator implements ProductValidator {
    @Override
    public void validate(Object object) throws ProductValidationException {
        checkInstantiation(object);
    }
}
