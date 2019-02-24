package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;

public class CreateCartAction implements Action {
    private static final String ACTION_NAME = "Create Cart";

    private final ShoppingCartService shoppingCartService;

    public CreateCartAction(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        String name = readFromConsole("Shopping cart name");

        try {
            shoppingCartService.create(name);
            System.out.println("Shopping Cart \"" + name + "\" has been created");
        } catch (RuntimeException e){
            System.out.println("Error! " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
