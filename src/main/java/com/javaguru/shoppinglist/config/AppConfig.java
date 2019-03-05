package com.javaguru.shoppinglist.config;

import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;
import com.javaguru.shoppinglist.service.validation.product.ProductDiscountValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductNameValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductPriceValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartNameValidator;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;
import com.javaguru.shoppinglist.userinterface.*;
import com.javaguru.shoppinglist.userinterface.product.CreateProductAction;
import com.javaguru.shoppinglist.userinterface.product.FindProductByIdAction;
import com.javaguru.shoppinglist.userinterface.shoppingcart.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@ComponentScan(basePackages = "com.javaguru.shoppinglist")
public class AppConfig {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;


    /* Console UI */
    @Bean
    public ConsoleUI getConsoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(findProductByIdAction());
        actions.add(createProductAction());
        actions.add(shoppingCartMenuAction());
        actions.add(exitAction());

        return new ConsoleUI(actions);
    }

    @Bean
    public Action findProductByIdAction() {
        return new FindProductByIdAction(productService);
    }

    @Bean
    public Action createProductAction() {
        return new CreateProductAction(productService);
    }

    @Bean
    public Action shoppingCartMenuAction() {
        return new ShoppingCartMenuAction(shoppingCartUI());
    }

    @Bean
    public Action exitAction() {
        return new ExitAction();
    }
    /* ----- */

    /* Shoppinc Cart SubConsole */
    @Bean
    public SubConsoleUI shoppingCartUI() {
        List<Action> shoppingCartActions = new ArrayList<>();
        shoppingCartActions.add(createCartAction());
        shoppingCartActions.add(addProductToCartAction());
        shoppingCartActions.add(findCartByNameAction());
        shoppingCartActions.add(getCartContentsAction());
        shoppingCartActions.add(getCartTotalCostAction());
        shoppingCartActions.add(removeCartAction());

        return new SubConsoleUI(shoppingCartActions);
    }

    @Bean
    public Action createCartAction() {
        return new CreateCartAction(shoppingCartService);
    }

    @Bean
    public Action findCartByNameAction() {
        return new FindCartByNameAction(shoppingCartService);
    }

    @Bean
    public Action getCartTotalCostAction() {
        return new GetCartTotalCostAction(shoppingCartService);
    }

    @Bean
    public Action getCartContentsAction() {
        return new GetCartContentsAction(shoppingCartService);
    }

    @Bean
    public Action addProductToCartAction() {
        return new AddProductToCartAction(shoppingCartService, productService);
    }

    @Bean
    public Action removeCartAction() {
        return new RemoveCartAction(shoppingCartService);
    }
    /* ----- */

    /* Product Validation Service */
    @Bean
    public ProductValidationService obligatoryValidationService() {
        Set<ValidatorInterface> productValidators = new HashSet<>();
        productValidators.add(productNameValidator());
        productValidators.add(productPriceValidator());
        productValidators.add(productDiscountValidator());

        return new ProductValidationService(productValidators);
    }

    @Bean
    public ValidatorInterface productNameValidator() {
        return new ProductNameValidator(productRepository);
    }

    @Bean
    public ValidatorInterface productPriceValidator() {
        return new ProductPriceValidator();
    }

    @Bean
    public ValidatorInterface productDiscountValidator() {
        return new ProductDiscountValidator();
    }
    /* ----- */

    /* ShoppingCart Validation Service */
    @Bean
    public ShoppingCartValidationService shoppingCartValidationService() {
        Set<ValidatorInterface> shoppingCartValidators = new HashSet<>();
        shoppingCartValidators.add(shoppingCartNameValidator());

        return new ShoppingCartValidationService(shoppingCartValidators);
    }

    @Bean
    public ValidatorInterface shoppingCartNameValidator() {
        return new ShoppingCartNameValidator(shoppingCartRepository);
    }
    /* ----- */
}
