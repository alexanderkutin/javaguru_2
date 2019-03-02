package com.javaguru.shoppinglist.service.validation.product;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

import java.math.BigDecimal;

public class ProductDiscountValidator implements ValidatorInterface<Product> {

    private void verifyPriceOnDiscount(Product product) {
        checkInstantiation(product.getPrice());
        BigDecimal maxPrice = new BigDecimal(20);
        if(product.getPrice().compareTo(maxPrice) < 0){
            product.setDiscount(new BigDecimal(0));
        }
    }

    private void checkDiscountLessThanMax(BigDecimal discount) {
        BigDecimal maxDiscount = new BigDecimal(100);
        if (discount.compareTo(maxDiscount) > 0){
            throw new ProductValidationException("Discount cannot be more that 100%");
        }
    }

    private void checkIsPositive(int signum) {
        if (signum < 0){
            throw new ProductValidationException("Discount cannot be negative");
        }
    }

    @Override
    public void validate(Product product) {
        checkInstantiation(product.getDiscount());
        verifyPriceOnDiscount(product);
        checkDiscountLessThanMax(product.getDiscount());
        checkIsPositive(product.getDiscount().signum());
    }
}
