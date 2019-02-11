package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Repository.ProductNotFoundException;
import com.javaguru.shoppinglist.Service.Product;

public class ProductPriceValidatorInterface implements ProductValidatorInterface {

    private void checkIsPositive(int signum) throws ProductNotFoundException {
        if (signum <= 0){
            throw new ProductValidationException("Price must be greater than zero");
        }
    }

    @Override
    public void validate(Product product) throws ProductValidationException {
        checkInstantiation(product);
        checkInstantiation(product.getPrice());
        checkIsPositive(product.getPrice().signum());
    }
}
