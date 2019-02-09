package com.javaguru.shoppinglist.Repository;

import com.javaguru.shoppinglist.Service.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<Long, Product> database = new HashMap<>();
    private Long PRODUCT_ID_SEQUENCE = 0L;

    public Product insertProduct(Product product){

        product.setId(PRODUCT_ID_SEQUENCE);
        database.put(PRODUCT_ID_SEQUENCE, product);
        PRODUCT_ID_SEQUENCE++;
        return product;
    }

    public Product getProductById(Long id) throws ProductNotFoundException {

        if(!database.containsKey(id)){
            throw new ProductNotFoundException("Product with ID:" + id + " does not exist");
        }
        return database.get(id);
    }

}
