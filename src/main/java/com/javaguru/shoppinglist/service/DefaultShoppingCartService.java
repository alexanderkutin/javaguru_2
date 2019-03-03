package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;

import java.util.NoSuchElementException;
import java.util.Optional;


public class DefaultShoppingCartService implements ShoppingCartService {
    static class FactoryHelper{
        ShoppingCart makeShoppingCart(String name){
            return new ShoppingCart(name);
        }
    }

    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartValidationService validationService;
    private FactoryHelper shoppingCartFactory;

    private DefaultShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService validationService, FactoryHelper shoppingCartFactory) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.validationService = validationService;
        this.shoppingCartFactory = shoppingCartFactory;
    }

    public DefaultShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService validationService) {
        this(shoppingCartRepository, validationService, new FactoryHelper());
    }

    @Override
    public void create(String name) {
        ShoppingCart newCart = shoppingCartFactory.makeShoppingCart(name);
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
        return optionalShoppingCart
                .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + name + "\" does not exist"));
    }

    @Override
    public boolean deleteByName(String name) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findCartByName(name);
        return shoppingCartRepository.removeCart(optionalCart
                .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + name + "\" does not exist")));
    }
}
