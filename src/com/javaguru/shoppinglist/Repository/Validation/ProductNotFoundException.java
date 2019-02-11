package com.javaguru.shoppinglist.Repository.Validation;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message){
        super(message);
    }
}
