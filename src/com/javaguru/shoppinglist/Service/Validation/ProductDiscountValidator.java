package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.math.BigDecimal;

public class ProductDiscountValidator implements ProductValidator {

    private void checkComparsionResultIsNegative(int result) throws ProductValidationException {
        if (result > 0){
            throw new ProductValidationException("Discount cannot be more that 100%");
        }
    }

    private void checkIsPositive(int signum) throws ProductValidationException {
        if (signum < 0){
            throw new ProductValidationException("Discount cannot be negative");
        }
    }

    @Override
    public void validate(Object productObj) throws ProductValidationException {
        Product product = checkProductInstantiation(productObj);
        checkInstantiation(product.getPrice());
        checkComparsionResultIsNegative(product.getDiscount().compareTo((new BigDecimal(100))));
        checkIsPositive(product.getDiscount().signum());
    }
}
