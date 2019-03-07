package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.userinterface.Action;
import com.javaguru.shoppinglist.userinterface.ConsoleUI;
import com.javaguru.shoppinglist.userinterface.SubConsoleUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUiConfig extends ConsoleActionConfig {
    /* Shoppinc Cart SubConsole */
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

    /* Console UI */
    @Bean
    public ConsoleUI getConsoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(findProductByIdAction);
        actions.add(createProductAction);
        actions.add(shoppingCartMenuAction);
        actions.add(exitAction);

        return new ConsoleUI(actions);
    }
}
