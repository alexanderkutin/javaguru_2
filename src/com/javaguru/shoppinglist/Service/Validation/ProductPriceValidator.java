package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Repository.Validation.ProductNotFoundException;
import com.javaguru.shoppinglist.Service.Product;

public class ProductPriceValidator implements ProductValidator {

    private void checkIsPositive(int signum) throws ProductNotFoundException {
        if (signum <= 0){
            throw new ProductValidationException("Price must be greater than zero");
        }
    }

    @Override
    public void validate(Object productObj) throws ProductValidationException {
        Product product = checkProductInstantiation(productObj);
        checkInstantiation(product.getPrice());
        checkIsPositive(product.getPrice().signum());
    }
}
