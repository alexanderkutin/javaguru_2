package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public class ProductNameValidatorInterface implements ProductValidatorInterface {

    private void checkValue(int length){
        if ((length < 3) || (length > 32)){
            throw new ProductValidationException("Name must be between 3 and 32 symbols ");
        }
    }

    @Override
    public void validate(Product product) throws ProductValidationException {
        checkInstantiation(product);
        checkInstantiation(product.getName());
        checkValue(product.getName().length());
    }
}
