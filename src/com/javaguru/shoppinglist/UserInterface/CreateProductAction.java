package com.javaguru.shoppinglist.UserInterface;

import com.javaguru.shoppinglist.Service.Category;
import com.javaguru.shoppinglist.Service.Product;
import com.javaguru.shoppinglist.Service.ProductService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        String price = scanner.nextLine();
        System.out.println("Enter product discount: ");
        String discount = scanner.nextLine();
        System.out.println("Enter product category: ");
        System.out.print(Arrays.asList(Category.values()) + ": ");
        String category = scanner.nextLine();
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();

        Product product = new Product();
        product.setName(name);
        product.setPrice(new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN));
        product.setDiscount(new BigDecimal(discount));
        product.setCategory(Category.valueOf(category.toUpperCase()));
        product.setDescription(description);

        try {
            Long response = productService.create(product);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}