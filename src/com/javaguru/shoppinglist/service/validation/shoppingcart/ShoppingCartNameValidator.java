package com.javaguru.shoppinglist.service.validation.shoppingcart;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationException;

import java.util.Optional;

public class ShoppingCartNameValidator implements ValidatorInterface {
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartNameValidator(ShoppingCartRepository shoppingCartRepository){
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public void checkDuplicateNameInRepository(String name){
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.getCartByName(name);
        if(optionalCart.isPresent()){
            throw new IllegalArgumentException("Name \"" + name + "\", already exists");
        }
    }

    @Override
    public void validate(Object object) throws ProductValidationException {
        ShoppingCart shoppingCart = shoppingCartInstantiation(object);
        checkInstantiation(shoppingCart.getName());
        checkDuplicateNameInRepository(shoppingCart.getName());
    }
}
