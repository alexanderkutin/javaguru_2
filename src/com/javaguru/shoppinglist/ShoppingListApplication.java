package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.Repository.ProductRepository;
import com.javaguru.shoppinglist.Service.DefaultProductService;
import com.javaguru.shoppinglist.Service.ProductService;
import com.javaguru.shoppinglist.Service.Validation.ProductDiscountValidator;
import com.javaguru.shoppinglist.Service.Validation.ProductNameValidator;
import com.javaguru.shoppinglist.Service.Validation.ProductPriceValidator;
import com.javaguru.shoppinglist.Service.Validation.ProductValidationService;
import com.javaguru.shoppinglist.UserInterface.*;

import java.util.ArrayList;
import java.util.List;

class ShoppingListApplication {

    public static void main(String[] args) {

        ProductRepository productRepository = new ProductRepository();

        ProductValidationService obligatoryValidationService = new ProductValidationService();
        obligatoryValidationService.addValidator(new ProductNameValidator());
        obligatoryValidationService.addValidator(new ProductPriceValidator());
        obligatoryValidationService.addValidator(new ProductDiscountValidator());

        ProductService productService = new DefaultProductService(productRepository, obligatoryValidationService);

        Action exitAction = new ExitAction();
        Action createUserAction = new CreateProductAction(productService);
        Action findUserByIdAction = new FindProductByIdAction(productService);

        List<Action> actions = new ArrayList<>();
        actions.add(findUserByIdAction);
        actions.add(createUserAction);
        actions.add(exitAction);

        ConsoleUI ui = new ConsoleUI(actions);
        ui.start();
    }
}
