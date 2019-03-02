package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;

import java.util.NoSuchElementException;

public class FindCartByNameAction implements Action {

    private static final String ACTION_NAME = "Find Product Cart by Name";

    private final ShoppingCartService shoppingCartService;

    public FindCartByNameAction(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        String name = readFromConsole("Cart name");

        try {
            ShoppingCart returnedCart = shoppingCartService.findByName(name);
            System.out.println(returnedCart);
        } catch (NoSuchElementException el) {
            System.out.println("Shopping Cart \"" + name + "\" does not exist");
        } catch (RuntimeException e){
            System.out.println("Error! " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
