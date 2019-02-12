package com.javaguru.shoppinglist.Repository.Validation;

import com.javaguru.shoppinglist.Service.Product;

import java.util.Map;

public class RepositoryDuplicateNameValidator {
    private Map<Long, Product> database;

    public RepositoryDuplicateNameValidator(Map<Long, Product> database){
        this.database = database;
    }

    private void checkEqualNames(String nameOne, String nameTwo) throws IllegalArgumentException {
        if(nameOne.equals(nameTwo)){
            throw new IllegalArgumentException("Product with Name: " + nameOne + ", already exists");
        }
    }

    public void validate(String name) throws IllegalArgumentException {
        database.forEach((productId, product) -> {
            checkEqualNames(name, product.getName());
        });
    }
}
