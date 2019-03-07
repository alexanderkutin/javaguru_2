package com.javaguru.shoppinglist.userinterface.product;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.userinterface.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class FindProductByIdAction implements Action {

    private static final String ACTION_NAME = "Find by ID";

    private final ProductService productService;

    @Autowired
    public FindProductByIdAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        String id = readFromConsole("Product ID");

        try {
            Product response = productService.findBy(Long.valueOf(id));
            System.out.println("Response: " + response);
        } catch (NumberFormatException en){
            System.out.println("ID must be entered as decimal number");
        } catch (NoSuchElementException el){
            System.out.println(el.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
