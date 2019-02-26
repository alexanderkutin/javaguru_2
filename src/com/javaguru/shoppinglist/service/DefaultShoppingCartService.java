package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;

import java.util.Optional;


public class DefaultShoppingCartService implements ShoppingCartService {
    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartValidationService validationService;

    public DefaultShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService validationService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.validationService = validationService;
    }

    @Override
    public void create(String name) {
        ShoppingCart newCart = new ShoppingCart(name);
        validationService.validate(newCart);
        shoppingCartRepository.insertCart(newCart);
    }

    @Override
    public boolean addProductToCart(ShoppingCart shoppingCart, Product product) {
        return shoppingCart.insertProduct(product);
    }

    @Override
    public ShoppingCart findByName(String name) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findCartByName(name);
        return optionalShoppingCart.get();
    }

    @Override
    public boolean deleteByName(String name) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findCartByName(name);
        return shoppingCartRepository.removeCart(optionalCart.get());
    }
}
