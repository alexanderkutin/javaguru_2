package com.javaguru.shoppinglist.config;


import com.javaguru.shoppinglist.userinterface.Action;
import com.javaguru.shoppinglist.userinterface.SubConsoleUI;
import com.javaguru.shoppinglist.userinterface.shoppingcart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ShoppingCartUiConfig {

    private CreateCartAction createCartAction;
    private AddProductToCartAction addProductToCartAction;
    private FindCartByNameAction findCartByNameAction;
    private GetCartContentsAction getCartContentsAction;
    private GetCartTotalCostAction getCartTotalCostAction;
    private RemoveCartAction removeCartAction;

    @Autowired
    public ShoppingCartUiConfig(CreateCartAction createCartAction, AddProductToCartAction addProductToCartAction, FindCartByNameAction findCartByNameAction, GetCartContentsAction getCartContentsAction, GetCartTotalCostAction getCartTotalCostAction, RemoveCartAction removeCartAction) {
        this.createCartAction = createCartAction;
        this.addProductToCartAction = addProductToCartAction;
        this.findCartByNameAction = findCartByNameAction;
        this.getCartContentsAction = getCartContentsAction;
        this.getCartTotalCostAction = getCartTotalCostAction;
        this.removeCartAction = removeCartAction;
    }

    @Bean
    public SubConsoleUI shoppingCartUI() {
        List<Action> shoppingCartActions = new ArrayList<>();
        shoppingCartActions.add(createCartAction);
        shoppingCartActions.add(addProductToCartAction);
        shoppingCartActions.add(findCartByNameAction);
        shoppingCartActions.add(getCartContentsAction);
        shoppingCartActions.add(getCartTotalCostAction);
        shoppingCartActions.add(removeCartAction);

        return new SubConsoleUI(shoppingCartActions);
    }
}
