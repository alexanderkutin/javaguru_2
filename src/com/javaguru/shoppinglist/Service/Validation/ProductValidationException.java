package com.javaguru.shoppinglist.Service.Validation;

public class ProductValidationException extends RuntimeException {
    public ProductValidationException(String message){
        super(message);
    }
}
