package com.javaguru.shoppinglist.service.validation;

public class ObjectValidator implements ValidatorInterface<Object> {
    @Override
    public void validate(Object object) {
        checkInstantiation(object);
    }
}
