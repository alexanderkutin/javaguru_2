package com.javaguru.shoppinglist.service.validation.shoppingcart;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCartValidationService {
    private Set<ValidatorInterface> productValidatorSet = new HashSet<>();

    public void addValidator(ValidatorInterface newShoppingCartValidator) {
        productValidatorSet.add(newShoppingCartValidator);
    }

    public void validate(ShoppingCart shoppingCart) {
        productValidatorSet.forEach(s -> s.validate(shoppingCart));
    }
}
