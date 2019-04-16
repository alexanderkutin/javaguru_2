package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartConverter {

    public ShoppingCart convert(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartDTO.getId());
        shoppingCart.setName(shoppingCartDTO.getName());
        return shoppingCart;
    }

    public ShoppingCartDTO convert(ShoppingCart shoppingCart) {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setName(shoppingCart.getName());
        return shoppingCartDTO;
    }
}
