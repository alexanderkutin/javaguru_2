package com.javaguru.shoppinglist.Repository.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.util.Map;

public class RepositoryObjectPresenceValidator {
    private Map<Long, Product> database;

    public RepositoryObjectPresenceValidator(Map<Long, Product> database){
        this.database = database;
    }

    public void validate(Long id) throws ProductNotFoundException {
        if(!database.containsKey(id)){
            throw new ProductNotFoundException("Product with ID:" + id + " does not exist");
        }
    }
}
