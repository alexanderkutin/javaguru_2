package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;

public interface ShoppingCartService {
    void create(String name);
    ShoppingCart findByName(String name);
    boolean deleteByName(String name);
    boolean addProductToCart(ShoppingCart shoppingCart, Product product);
}
