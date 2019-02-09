package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public class ProductNameValidator implements ProductCoreValidator {

    @Override
    public void validate(Product product) throws ProductValidationException {
        checkInstantiation(product);

        if(product.getName() == null){
            throw new ProductValidationException("Product name is null");
        }

        if ((product.getName().length() < 3) || (product.getName().length() > 32)){
            throw new ProductValidationException("Name must be between 3 and 32 symbols ");
        }
    }
}
