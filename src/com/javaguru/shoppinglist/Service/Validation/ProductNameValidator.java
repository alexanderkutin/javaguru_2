package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public class ProductNameValidator implements ProductValidator {

    private void checkValue(int length) throws ProductValidationException {
        if ((length < 3) || (length > 32)){
            throw new ProductValidationException("Name must be between 3 and 32 symbols ");
        }
    }

    @Override
    public void validate(Object productObj) throws ProductValidationException {
        Product product = checkProductInstantiation(productObj);
        checkInstantiation(product.getName());
        checkValue(product.getName().length());
    }
}
