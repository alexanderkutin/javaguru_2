package com.javaguru.shoppinglist.domain;

//import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    @Id
    @Column(name = "id",  nullable = false, unique = true)
    /*For Hibernate v4*/
    @GeneratedValue(strategy = GenerationType.AUTO)
    /*For Hibernate v5*/
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    //@GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "discount")
    private BigDecimal discount;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;
    @Column(name = "description")
    private String description;

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
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getDiscount() { return discount; }

    public void setDiscount(BigDecimal discount){ this.discount = discount; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }


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
                ", category=" + category +
                ", price=" + price +
                ", discount=" + discount + "%" +
                ", actual price=" + price.divide(ONE_HUNDRED, 2).multiply(ONE_HUNDRED.subtract(discount)) +
                ", description='" + description + '\'' +
                '}';
    }
}
