package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.DefaultProductService;
import com.javaguru.shoppinglist.service.DefaultShoppingCartService;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.service.validation.ObjectValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductDiscountValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductNameValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductPriceValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import com.javaguru.shoppinglist.userinterface.*;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartNameValidator;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;
import com.javaguru.shoppinglist.userinterface.product.CreateProductAction;
import com.javaguru.shoppinglist.userinterface.product.FindProductByIdAction;
import com.javaguru.shoppinglist.userinterface.shoppingcart.*;

import java.util.ArrayList;
import java.util.List;

class ShoppingListApplication {

    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();

        ProductValidationService obligatoryValidationService = new ProductValidationService();
        obligatoryValidationService.addValidator(new ProductNameValidator(productRepository));
        obligatoryValidationService.addValidator(new ProductPriceValidator());
        obligatoryValidationService.addValidator(new ProductDiscountValidator());

        ShoppingCartValidationService shoppingCartValidationService = new ShoppingCartValidationService();
        shoppingCartValidationService.addValidator(new ShoppingCartNameValidator(shoppingCartRepository));

        ProductService productService = new DefaultProductService(productRepository, obligatoryValidationService);
        ShoppingCartService shoppingCartService = new DefaultShoppingCartService(shoppingCartRepository, shoppingCartValidationService, new ObjectValidator());

        Action createCartAction = new CreateCartAction(shoppingCartService);
        Action findCartByNameAction = new FindCartByNameAction(shoppingCartService);
        Action getCartTotalCostAction = new GetCartTotalCostAction(shoppingCartService);
        Action getCartContentsAction = new GetCartContentsAction(shoppingCartService);
        Action addProductToCartAction = new AddProductToCartAction(shoppingCartService, productService);
        Action removeCartAction = new RemoveCartAction(shoppingCartService);

        List<Action> shoppingCartActions = new ArrayList<>();
        shoppingCartActions.add(createCartAction);
        shoppingCartActions.add(addProductToCartAction);
        shoppingCartActions.add(findCartByNameAction);
        shoppingCartActions.add(getCartContentsAction);
        shoppingCartActions.add(getCartTotalCostAction);
        shoppingCartActions.add(removeCartAction);

        SubConsoleUI shoppingCartUI = new SubConsoleUI(shoppingCartActions);

        Action exitAction = new ExitAction();
        Action createUserAction = new CreateProductAction(productService);
        Action findUserByIdAction = new FindProductByIdAction(productService);
        Action shoppingCartMenuAction = new ShoppingCartMenuAction(shoppingCartUI);

        List<Action> actions = new ArrayList<>();
        actions.add(findUserByIdAction);
        actions.add(createUserAction);
        actions.add(shoppingCartMenuAction);
        actions.add(exitAction);

        ConsoleUI ui = new ConsoleUI(actions);
        ui.start();
    }
}
