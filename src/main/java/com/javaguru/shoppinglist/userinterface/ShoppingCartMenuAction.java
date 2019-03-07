package com.javaguru.shoppinglist.userinterface;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMenuAction implements Action {

    private static final String ACTION_NAME = "Shopping Cart Menu";

    private SubConsoleUI shoppingCartMenu;

    @Autowired
    public ShoppingCartMenuAction(SubConsoleUI shoppingCartMenu) {
        this.shoppingCartMenu = shoppingCartMenu;
    }

    @Override
    public void execute() {
        shoppingCartMenu.start();
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
