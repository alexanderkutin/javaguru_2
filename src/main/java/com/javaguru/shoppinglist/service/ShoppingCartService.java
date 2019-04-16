package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;

public interface ShoppingCartService {
    Long create(ShoppingCartDTO shoppingCartDTO);
    ShoppingCartDTO findByName(String name);
    ShoppingCartDTO findById(Long id);
    void deleteByName(String name);
    void deleteById(Long id);
    boolean addProductToCart(String shoppingCartName, Long productId);
}
