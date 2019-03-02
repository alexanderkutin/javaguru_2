package com.javaguru.shoppinglist.service.validation.product;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ValidatorInterface;

import java.util.Optional;

public class ProductNameValidator implements ValidatorInterface<Product> {
    private ProductRepository productRepository;

    public ProductNameValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void checkNamePresenceInProductRepository(String name) {
        Optional<Product> optionalProduct = productRepository.findProductByName(name);
        if(optionalProduct.isPresent()){
            throw new ProductValidationException("Name \"" + name + "\", already exists");
        }
    }

    private void checkLengthValue(int length) {
        if ((length < 3) || (length > 32)){
            throw new ProductValidationException("Name must be between 3 and 32 symbols ");
        }
    }

    @Override
    public void validate(Product product) {
        checkInstantiation(product.getName());
        checkLengthValue(product.getName().length());
        checkNamePresenceInProductRepository(product.getName());
    }
}
