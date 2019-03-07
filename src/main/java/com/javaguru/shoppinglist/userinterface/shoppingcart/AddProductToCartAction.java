package com.javaguru.shoppinglist.userinterface.shoppingcart;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.ShoppingCartService;
import com.javaguru.shoppinglist.userinterface.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class AddProductToCartAction implements Action {

    private static final String ACTION_NAME = "Add Product to Shopping Cart";

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
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

    private Optional<ShoppingCart> getCart(String cartName){
        try {
            return Optional.of(shoppingCartService.findByName(cartName));
        } catch (NoSuchElementException el) {
            System.out.println("Shopping Cart \"" + cartName + "\" does not exist");
            return Optional.empty();
        }
    }

    private Optional<Product> getProduct(String id){
        try {
            return  Optional.of(productService.findBy(Long.valueOf(id)));
        } catch (NumberFormatException en){
            System.out.println("ID must be entered as decimal number");
            return Optional.empty();
        } catch (NoSuchElementException el){
            System.out.println("Product with ID:" + id + " does not exist");
            return Optional.empty();
        }
    }

    @Override
    public void execute() {
        String cartName = readFromConsole("Shopping Cart name");
        if(!getCart(cartName).isPresent())
            return;

        String id = readFromConsole("Product ID");
        if(!getProduct(id).isPresent())
            return;

        ShoppingCart returnedCart = getCart(cartName).get();
        Product returnedProduct = getProduct(id).get();

        boolean isProductAddedToCart = shoppingCartService.addProductToCart(returnedCart, returnedProduct);
        printResult(isProductAddedToCart, returnedCart.getName(), returnedProduct.getName());
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
