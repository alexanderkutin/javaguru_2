package com.javaguru.shoppinglist.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {
    private String name;
    private List<Product> cartProductList = new LinkedList<>();

    public ShoppingCart(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean insertProduct(Product product){
        return cartProductList.add(product);
    }

    public List<String> getContents(){
        List<String> feedbackMessageArray = new ArrayList<>();

        cartProductList.forEach(product ->
            feedbackMessageArray.add(product.toString())
        );

        if(feedbackMessageArray.isEmpty()){
            feedbackMessageArray.add("This cart is empty");
        }

        return feedbackMessageArray;
    }

    public BigDecimal getTotalCost(){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for(Product product : cartProductList){
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "Name = '" + name + '\'' +
                ", Products inside = " + cartProductList.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(cartProductList, that.cartProductList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
