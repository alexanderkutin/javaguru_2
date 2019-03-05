package com.javaguru.shoppinglist.service.validation;

import org.springframework.stereotype.Component;

@Component
public class ObjectValidator implements ValidatorInterface<Object> {
    @Override
    public void validate(Object object) {
        checkInstantiation(object);
    }
}
