package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ShoppingCartRepository {
    Set<ShoppingCart> database = new HashSet<>();

    public void insertCart(ShoppingCart shoppingCart) {
        database.add(shoppingCart);
    }

    public Optional<ShoppingCart> findCartByName(String name) {
        return database.stream()
                .filter(shoppingCart -> shoppingCart.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public boolean removeCart(ShoppingCart shoppingCart) {
        return database.remove(shoppingCart);
    }
}
