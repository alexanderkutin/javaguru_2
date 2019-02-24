package com.javaguru.shoppinglist.service.validation.product;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

public class ProductPriceValidator implements ValidatorInterface {

    private void checkIsPositive(int signum) {
        if (signum <= 0){
            throw new ProductValidationException("Price must be greater than zero");
        }
    }

    @Override
    public void validate(Object productObj) throws ProductValidationException {
        Product product = productInstantiation(productObj);
        checkInstantiation(product.getPrice());
        checkIsPositive(product.getPrice().signum());
    }
}
