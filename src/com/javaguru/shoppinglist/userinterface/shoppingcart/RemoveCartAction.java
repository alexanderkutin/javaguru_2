package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;

import java.util.NoSuchElementException;

public class RemoveCartAction implements Action {

    private static final String ACTION_NAME = "Remove Shopping Cart";

    private final ShoppingCartService shoppingCartService;

    public RemoveCartAction(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        String cartName = readFromConsole("Shopping Cart name");

        try {
            shoppingCartService.deleteByName(cartName);
            System.out.println("Shopping Cart \"" + cartName + "\" has been removed");
        } catch (NoSuchElementException el) {
            System.out.println("Shopping Cart \"" + cartName + "\" does not exist");
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
