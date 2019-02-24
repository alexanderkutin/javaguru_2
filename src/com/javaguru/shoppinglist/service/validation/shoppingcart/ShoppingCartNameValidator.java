package com.javaguru.shoppinglist.service.validation.shoppingcart;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

import java.util.Optional;

public class ShoppingCartNameValidator implements ValidatorInterface<ShoppingCart> {
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartNameValidator(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void checkDuplicateNameInRepository(String name) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.getCartByName(name);
        if(optionalCart.isPresent()){
            throw new IllegalArgumentException("Name \"" + name + "\", already exists");
        }
    }

    @Override
    public void validate(ShoppingCart shoppingCart) {
        checkInstantiation(shoppingCart.getName());
        checkDuplicateNameInRepository(shoppingCart.getName());
    }
}
