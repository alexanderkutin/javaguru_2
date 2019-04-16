package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.util.Optional;

public interface ShoppingCartRepository {
    Long saveCart(ShoppingCart shoppingCart);
    Optional<ShoppingCart> findCartById(Long id);
    Optional<ShoppingCart> findCartByName(String name);
    void removeCart(ShoppingCart shoppingCart);
}
