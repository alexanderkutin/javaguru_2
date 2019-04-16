package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Profile("hibernate")
public class ShoppingCartRepositoryInMemory implements ShoppingCartRepository {

    private Long idCounter = Long.valueOf(0);
    Set<ShoppingCart> database = new HashSet<>();

    @Override
    public Long saveCart(ShoppingCart shoppingCart) {
        assignId(shoppingCart);
        database.add(shoppingCart);
        return shoppingCart.getId();
    }

    @Override
    public Optional<ShoppingCart> findCartById(Long id) {
        return database.stream()
                .filter(shoppingCart -> shoppingCart.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<ShoppingCart> findCartByName(String name) {
        return database.stream()
                .filter(shoppingCart -> shoppingCart.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public void removeCart(ShoppingCart shoppingCart) {
        database.remove(shoppingCart);
    }

    private void assignId(ShoppingCart shoppingCart) {
        idCounter++;
        shoppingCart.setId(idCounter);
    }
}
