package com.javaguru.shoppinglist.Repository.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.util.Map;

public class RepositoryDuplicateNameValidator {
    private Map<Long, Product> database;

    public RepositoryDuplicateNameValidator(Map<Long, Product> database){
        this.database = database;
    }

    public void validate(String name) throws IllegalArgumentException {
        database.forEach((k, v) -> {
            if(name.equals(v.getName())){
                throw new IllegalArgumentException("Product with Name: " + name + ", already exists");
            }
        });

    }
}
