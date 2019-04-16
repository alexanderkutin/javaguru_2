package com.javaguru.shoppinglist.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingCart {

    private Long id;
    private String name;
    private List<Product> productList = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


    public boolean insertProduct(Product product) {
        return productList.add(product);
    }

    public List<String> getContents() {
        List<String> feedbackMessageArray = new ArrayList<>();

        productList.forEach(product ->
            feedbackMessageArray.add(product.toString())
        );

        if(feedbackMessageArray.isEmpty()){
            feedbackMessageArray.add("This cart is empty");
        }

        return feedbackMessageArray;
    }


    public BigDecimal getTotalCost() {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(Product product : productList){

            totalPrice = totalPrice.add(product.getPrice());

        }

        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productList);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" + productList +
                '}';
    }
}
