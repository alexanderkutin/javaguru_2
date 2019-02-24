package com.javaguru.shoppinglist.service.validation.product;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

import java.util.HashSet;
import java.util.Set;

public class ProductValidationService {
    private Set<ValidatorInterface> productValidatorSet = new HashSet<>();

    public void addValidator(ValidatorInterface newProductValidator) {
        productValidatorSet.add(newProductValidator);
    }

    public void validate(Product product) {
        productValidatorSet.forEach(s -> s.validate(product));
    }
}
