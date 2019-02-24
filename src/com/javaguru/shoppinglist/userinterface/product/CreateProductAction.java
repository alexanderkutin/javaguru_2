package com.javaguru.shoppinglist.userinterface.product;

import com.javaguru.shoppinglist.service.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.userinterface.Action;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;

    public CreateProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void execute() {
        String name = readFromConsole("product name");
        String price = readFromConsole("product price");
        String discount = readFromConsole("product discount");
        String category = readFromConsole("category\n" + Arrays.asList(Category.values()));
        String description = readFromConsole("description");

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
