package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class RemoveCartAction implements Action {

    private static final String ACTION_NAME = "Remove Shopping Cart";

    private final ShoppingCartService shoppingCartService;

    @Autowired
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
            System.out.println(el.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
