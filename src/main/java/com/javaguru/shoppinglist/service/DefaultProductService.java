package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.mapper.ProductConverter;
import com.javaguru.shoppinglist.repository.ProductRepository;
import com.javaguru.shoppinglist.service.validation.ObjectValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {
    private ProductRepository productRepository;
    private ProductValidationService obligatoryFieldValidationService;
    private ObjectValidator objectValidator;
    private ProductConverter converter;

    @Autowired
    public DefaultProductService(ProductRepository productRepository, ProductValidationService obligatoryFieldValidationService, ObjectValidator objectValidator, ProductConverter converter) {
        this.productRepository = productRepository;
        this.obligatoryFieldValidationService = obligatoryFieldValidationService;
        this.objectValidator = objectValidator;
        this.converter = converter;
    }

    private void setDiscountZeroIfPriceLessThan(Product product, BigDecimal maxPrice) {
        if(product.getPrice().compareTo(maxPrice) < 0){
            product.setDiscount(new BigDecimal(0));
        }
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new NoSuchElementException("Product with ID:" + id + " does not exist"));
    }

    @Override
    public ProductDTO findById(Long id) {
        objectValidator.validate(id);
        return productRepository.findProductById(id)
                .map(converter::convert)
                .orElseThrow(() -> new NoSuchElementException("Product with ID:" + id + " does not exist"));
    }

    @Override
    public Long create(ProductDTO productDTO) {
        Product product = converter.convert(productDTO);
        obligatoryFieldValidationService.validate(product);
        setDiscountZeroIfPriceLessThan(product, BigDecimal.valueOf(20));
        Product newProduct = productRepository.insertProduct(product);
        return newProduct.getId();
    }
}
