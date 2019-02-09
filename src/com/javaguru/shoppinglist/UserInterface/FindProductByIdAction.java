package com.javaguru.shoppinglist.UserInterface;

import com.javaguru.shoppinglist.Service.Product;
import com.javaguru.shoppinglist.Service.ProductService;

import java.util.Scanner;

public class FindProductByIdAction implements Action {

    private static final String ACTION_NAME = "Find by ID";

    private final ProductService productService;

    public FindProductByIdAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        Long id = scanner.nextLong();
        try {
            Product response = productService.findBy(id);
            System.out.println("Response: " + response);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
