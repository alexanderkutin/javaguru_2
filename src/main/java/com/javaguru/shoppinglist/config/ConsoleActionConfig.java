package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.userinterface.ExitAction;
import com.javaguru.shoppinglist.userinterface.ShoppingCartMenuAction;
import com.javaguru.shoppinglist.userinterface.product.CreateProductAction;
import com.javaguru.shoppinglist.userinterface.product.FindProductByIdAction;
import com.javaguru.shoppinglist.userinterface.shoppingcart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsoleActionConfig {

    @Autowired
    CreateCartAction createCartAction;

    @Autowired
    AddProductToCartAction addProductToCartAction;

    @Autowired
    FindCartByNameAction findCartByNameAction;

    @Autowired
    GetCartContentsAction getCartContentsAction;

    @Autowired
    GetCartTotalCostAction getCartTotalCostAction;

    @Autowired
    RemoveCartAction removeCartAction;


    @Autowired
    FindProductByIdAction findProductByIdAction;

    @Autowired
    CreateProductAction createProductAction;

    @Autowired
    ShoppingCartMenuAction shoppingCartMenuAction;

    @Autowired
    ExitAction exitAction;
}
