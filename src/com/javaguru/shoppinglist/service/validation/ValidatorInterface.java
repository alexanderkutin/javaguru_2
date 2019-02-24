package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationException;

public interface ValidatorInterface {

    void validate(Object object) throws ProductValidationException;

    default void checkInstantiation(Object object) {
        if(object == null){
            throw new ProductValidationException("Null object");
        }
    }

    default Product productInstantiation(Object object) {
        if ((object == null) || (Product.class != object.getClass())){
            throw new RuntimeException("Product object error");
        }
        return (Product)object;
    }

    default ShoppingCart shoppingCartInstantiation(Object object) {
        if((object == null) || (ShoppingCart.class != object.getClass())){
            throw new RuntimeException("ShoppingCart object error");
        }
        return (ShoppingCart)object;
    }
}
