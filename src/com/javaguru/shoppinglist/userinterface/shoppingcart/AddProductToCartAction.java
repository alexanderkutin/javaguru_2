package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;

import java.util.NoSuchElementException;

public class AddProductToCartAction implements Action {

    private static final String ACTION_NAME = "Add Product to Shopping Cart";

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    public AddProductToCartAction(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    private void printResult(boolean result, String cartName, String productName){
        if(result){
            System.out.println("Product \"" + productName + "\" has been added to shopping cart \"" + cartName + "\"");
        } else {
            System.out.println("Product \"" + productName + "\" already exists in shopping cart \"" + cartName + "\"");
        }
    }

    @Override
    public void execute() {
        ShoppingCart returnedCart = null;
        Product returnedProduct = null;

        String cartName = readFromConsole("Shopping Cart name");

        try {
            returnedCart = shoppingCartService.findByName(cartName);
        } catch (NoSuchElementException el) {
            System.out.println("Shopping Cart \"" + cartName + "\" does not exist");
            return;
        }

        String id = readFromConsole("Product ID");

        try {
            returnedProduct = productService.findBy(Long.valueOf(id));
        } catch (NumberFormatException en){
            System.out.println("ID must be entered as decimal number");
            return;
        } catch (NoSuchElementException el){
            System.out.println("Product with ID:" + id + " does not exist");
            return;
        }

        printResult(shoppingCartService.addProductToCart(returnedCart, returnedProduct),
                returnedCart.getName(),
                returnedProduct.getName()
        );
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
