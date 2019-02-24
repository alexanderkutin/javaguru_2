package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ShoppingCartRepository {
    Set<ShoppingCart> database = new HashSet<>();

    public void insertCart(ShoppingCart shoppingCart) {
        database.add(shoppingCart);
    }

    public Optional<ShoppingCart> getCartByName(String name) {
        return database.stream()
                .filter(shoppingCart -> shoppingCart.getName().equalsIgnoreCase(name))
                .findFirst();
        /*
        for(ShoppingCart shoppingCart : database){
            if(name.equals(shoppingCart.getName())){
                return Optional.of(shoppingCart);
            }
        }
        return Optional.empty();
        */
    }

    public boolean removeCart(ShoppingCart shoppingCart) {
        return database.remove(shoppingCart);
    }
}
