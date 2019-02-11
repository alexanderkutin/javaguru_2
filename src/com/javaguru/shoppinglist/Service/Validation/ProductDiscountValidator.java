package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.math.BigDecimal;

public class ProductDiscountValidator implements ProductValidator {

    private void verifyPriceOnDiscount(Product product){
        checkInstantiation(product.getPrice());
        BigDecimal maxPrice = new BigDecimal(20);
        if(product.getPrice().compareTo(maxPrice) < 0){
            product.setDiscount(new BigDecimal(0));
        }
    }

    private void checkDiscountLessThanMax(BigDecimal discount) throws ProductValidationException {
        BigDecimal maxDiscount = new BigDecimal(100);
        if (discount.compareTo(maxDiscount) > 0){
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
        checkInstantiation(product.getDiscount());
        verifyPriceOnDiscount(product);
        checkDiscountLessThanMax(product.getDiscount());
        checkIsPositive(product.getDiscount().signum());
    }
}
