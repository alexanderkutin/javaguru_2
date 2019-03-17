package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.service.validation.ValidatorInterface;
import com.javaguru.shoppinglist.service.validation.product.ProductDiscountValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductNameValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductPriceValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartNameValidator;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ValidationServiceConfig {

    private final ProductNameValidator productNameValidator;
    private final ProductPriceValidator productPriceValidator;
    private final ProductDiscountValidator productDiscountValidator;
    private final ShoppingCartNameValidator shoppingCartNameValidator;

    @Autowired
    public ValidationServiceConfig(ProductNameValidator productNameValidator, ProductPriceValidator productPriceValidator, ProductDiscountValidator productDiscountValidator, ShoppingCartNameValidator shoppingCartNameValidator) {
        this.productNameValidator = productNameValidator;
        this.productPriceValidator = productPriceValidator;
        this.productDiscountValidator = productDiscountValidator;
        this.shoppingCartNameValidator = shoppingCartNameValidator;
    }

    @Bean
    public ProductValidationService obligatoryValidationService() {
        Set<ValidatorInterface> productValidators = new HashSet<>();
        productValidators.add(productNameValidator);
        productValidators.add(productPriceValidator);
        productValidators.add(productDiscountValidator);

        return new ProductValidationService(productValidators);
    }

    @Bean
    public ShoppingCartValidationService shoppingCartValidationService() {
        Set<ValidatorInterface> shoppingCartValidators = new HashSet<>();
        shoppingCartValidators.add(shoppingCartNameValidator);

        return new ShoppingCartValidationService(shoppingCartValidators);
    }
}
