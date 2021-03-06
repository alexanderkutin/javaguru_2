package com.javaguru.shoppinglist.userinterface.product;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationException;
import com.javaguru.shoppinglist.userinterface.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Component
public class CreateProductAction implements Action {

    private static final String ACTION_NAME = "Create Product";

    private final ProductService productService;

    @Autowired
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
        product.setPrice(new BigDecimal(setZeroIfEmpty(price)).setScale(2, RoundingMode.HALF_EVEN));
        product.setDiscount(new BigDecimal(setZeroIfEmpty(discount)).setScale(4, RoundingMode.HALF_EVEN));
        product.setCategory(Category.valueOf(category.toUpperCase()));
        product.setDescription(description);

        try {
            Long response = productService.create(product);
            System.out.println("Response: " + response);
        } catch (ProductValidationException e) {
            System.out.println("Error! " + e.getMessage());
        } catch (NullPointerException eNull){
            System.out.println("Critical Error! " + eNull.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
