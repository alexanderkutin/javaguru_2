package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.service.validation.ValidatorInterface;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ValidationServiceConfig extends ValidatorsConfig {
    /* Product Validation Service */
    @Bean
    public ProductValidationService obligatoryValidationService() {
        Set<ValidatorInterface> productValidators = new HashSet<>();
        productValidators.add(productNameValidator);
        productValidators.add(productPriceValidator);
        productValidators.add(productDiscountValidator);

        return new ProductValidationService(productValidators);
    }

    /* ShoppingCart Validation Service */
    @Bean
    public ShoppingCartValidationService shoppingCartValidationService() {
        Set<ValidatorInterface> shoppingCartValidators = new HashSet<>();
        shoppingCartValidators.add(shoppingCartNameValidator);

        return new ShoppingCartValidationService(shoppingCartValidators);
    }
}
