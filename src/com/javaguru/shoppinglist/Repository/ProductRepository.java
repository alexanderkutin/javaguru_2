package com.javaguru.shoppinglist.Repository;

import com.javaguru.shoppinglist.Repository.Validation.ProductNotFoundException;
import com.javaguru.shoppinglist.Repository.Validation.RepositoryDuplicateNameValidator;
import com.javaguru.shoppinglist.Repository.Validation.RepositoryObjectPresenceValidator;
import com.javaguru.shoppinglist.Service.Product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;
    private RepositoryObjectPresenceValidator presenceValidator = new RepositoryObjectPresenceValidator(database);
    private RepositoryDuplicateNameValidator duplicateNameValidator = new RepositoryDuplicateNameValidator(database);

    public Product insertProduct(Product product) throws IllegalArgumentException {
        duplicateNameValidator.validate(product.getName());
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public Product getProductById(Long id) throws ProductNotFoundException {
        presenceValidator.validate(id);
        return database.get(id);
    }

}
