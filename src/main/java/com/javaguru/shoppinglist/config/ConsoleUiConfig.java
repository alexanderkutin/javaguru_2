package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.userinterface.*;
import com.javaguru.shoppinglist.userinterface.product.CreateProductAction;
import com.javaguru.shoppinglist.userinterface.product.FindProductByIdAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConsoleUiConfig {

    private FindProductByIdAction findProductByIdAction;
    private CreateProductAction createProductAction;
    private ShoppingCartMenuAction shoppingCartMenuAction;
    private ExitAction exitAction;

    @Autowired
    public ConsoleUiConfig(FindProductByIdAction findProductByIdAction, CreateProductAction createProductAction, ShoppingCartMenuAction shoppingCartMenuAction, ExitAction exitAction) {
        this.findProductByIdAction = findProductByIdAction;
        this.createProductAction = createProductAction;
        this.shoppingCartMenuAction = shoppingCartMenuAction;
        this.exitAction = exitAction;
    }

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
