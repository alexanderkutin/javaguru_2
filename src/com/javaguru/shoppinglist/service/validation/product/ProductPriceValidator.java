package com.javaguru.shoppinglist.service.validation.product;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

public class ProductPriceValidator implements ValidatorInterface<Product> {

    private void checkIsPositive(int signum) {
        if (signum <= 0){
            throw new ObjectValidationException("Price must be greater than zero");
        }
    }

    @Override
    public void validate(Product product) {
        checkInstantiation(product.getPrice());
        checkIsPositive(product.getPrice().signum());
    }
}
