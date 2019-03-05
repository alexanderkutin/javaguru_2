package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ObjectValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private ProductValidationService obligatoryFieldValidationService;
    private ObjectValidator objectValidator;

    @Autowired
    public DefaultProductService(ProductRepository productRepository, ProductValidationService obligatoryFieldValidationService, ObjectValidator objectValidator){
        this.productRepository = productRepository;
        this.obligatoryFieldValidationService = obligatoryFieldValidationService;
        this.objectValidator = objectValidator;
    }

    private void setDiscountZeroIfPriceLessThan(Product product, BigDecimal maxPrice) {
        if(product.getPrice().compareTo(maxPrice) < 0){
            product.setDiscount(new BigDecimal(0));
        }
    }

    @Override
    public Product findBy(Long id) {
        objectValidator.validate(id);
        Optional<Product> optionalProduct = productRepository.findProductById(id);
        return optionalProduct
                .orElseThrow(() -> new NoSuchElementException("Product with ID:" + id + " does not exist"));
    }

    @Override
    public Long create(Product product) {
        obligatoryFieldValidationService.validate(product);
        setDiscountZeroIfPriceLessThan(product, BigDecimal.valueOf(20));
        Product newProduct = productRepository.insertProduct(product);
        return newProduct.getId();
    }
}
