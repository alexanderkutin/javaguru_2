package com.javaguru.shoppinglist.userinterface;

public class ShoppingCartMenuAction implements Action {

    private static final String ACTION_NAME = "Shopping Cart Menu";

    private SubConsoleUI shoppingCartMenu;

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
