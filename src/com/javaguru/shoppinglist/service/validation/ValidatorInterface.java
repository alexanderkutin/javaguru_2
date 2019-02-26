package com.javaguru.shoppinglist.service.validation;

public interface ValidatorInterface<T> {

    void validate(T object);

    default void checkInstantiation(Object object) {
        if(object == null){
            throw new NullPointerException("Object must be not null");
        }
    }
}
