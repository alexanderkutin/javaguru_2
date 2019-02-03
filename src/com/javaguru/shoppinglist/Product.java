package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private String description;
    private Category category;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount(){ return discount; }

    public void setDiscount(BigDecimal discount){ this.discount = discount; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory(){ return category; }

    public void setCategory(Category category){ this.category = category; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(description, product.description) &&
                category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount, description, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", actual price=" + price.divide(ONE_HUNDRED, 0).multiply(ONE_HUNDRED.subtract(discount)) +
                ", discount=" + discount + "%" +
                ", description='" + description + '\'' +
                ", category=" + category +
                '}';
    }
}
