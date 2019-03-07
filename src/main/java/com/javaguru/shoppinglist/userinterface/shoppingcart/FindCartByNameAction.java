package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FindCartByNameAction implements Action {

    private static final String ACTION_NAME = "Find Product Cart by Name";

    private final ShoppingCartService shoppingCartService;

    @Autowired
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
            System.out.println(el.getMessage());
        } catch (RuntimeException e){
            System.out.println("Error! " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
