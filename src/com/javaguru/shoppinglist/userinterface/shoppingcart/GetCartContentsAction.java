package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;

import java.util.NoSuchElementException;

public class GetCartContentsAction implements Action {

    private static final String ACTION_NAME = "Show Product Cart contents";

    private final ShoppingCartService shoppingCartService;

    public GetCartContentsAction(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public void execute() {
        String name = readFromConsole("Cart name");

        try {
            ShoppingCart returnedCart = shoppingCartService.findByName(name);
            returnedCart.getContents().forEach(s -> System.out.println(s));
            System.out.println();
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
