package com.javaguru.shoppinglist.Service.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.math.BigDecimal;

public class ProductDiscountValidator implements ProductCoreValidator {

    @Override
    public void validate(Product product) throws ProductValidationException {
        checkInstantiation(product);

        if(product.getPrice() == null){
            throw new ProductValidationException("Product price is null");
        }

        if (product.getDiscount().compareTo((new BigDecimal(100))) > 0){
            throw new ProductValidationException("Discount cannot be more that 100%");
        }

        if (product.getDiscount().signum() < 0){
            throw new ProductValidationException("Discount cannot be negative");
        }
    }
}
