package com.javaguru.shoppinglist.mapper;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class ProductConverter {

    private String setZeroIfEmpty(String input) {
        if (input.isEmpty()) {
            return "0";
        }
        return input;
    }

    public Product convert(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(new BigDecimal(setZeroIfEmpty(productDTO.getPrice()))
                .setScale(2, RoundingMode.HALF_EVEN));
        product.setDiscount(new BigDecimal(setZeroIfEmpty(productDTO.getDiscount()))
                .setScale(4, RoundingMode.HALF_EVEN));
        product.setCategory(Category.valueOf(productDTO.getCategory().toUpperCase()));
        product.setDescription(productDTO.getDescription());
        return product;
    }

    public ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice().toString());
        productDTO.setDiscount(product.getDiscount().toString());
        productDTO.setCategory(product.getCategory().toString());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }
}
