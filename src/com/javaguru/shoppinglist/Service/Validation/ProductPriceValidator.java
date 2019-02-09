package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

public class ProductPriceValidator implements ProductCoreValidator {

    @Override
    public void validate(Product product) throws ProductValidationException {
        checkInstantiation(product);

        if(product.getPrice() == null){
            throw new ProductValidationException("Product price is null");
        }

        if (product.getPrice().signum() <= 0){
            throw new ProductValidationException("Price must be greater than zero");
        }
    }
}
