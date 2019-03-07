package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.service.validation.product.ProductDiscountValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductNameValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductPriceValidator;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorsConfig {

    @Autowired
    ProductNameValidator productNameValidator;

    @Autowired
    ProductPriceValidator productPriceValidator;

    @Autowired
    ProductDiscountValidator productDiscountValidator;

    @Autowired
    ShoppingCartNameValidator shoppingCartNameValidator;
}
