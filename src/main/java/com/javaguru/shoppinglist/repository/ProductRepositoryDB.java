package com.javaguru.shoppinglist.repository;


import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
@Profile({"local", "dev"})
public class ProductRepositoryDB implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        String query = "select * from product where name='" + name + "'";

        List<Product> products = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class));

        if(!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }

    @Override
    public Product insertProduct(Product product) {
        String query = "insert into product (name, price, discount, category, description) values" +
                "(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        System.out.println(product.getDiscount().toString());

        jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setBigDecimal(3, product.getDiscount());
            ps.setString(4, product.getCategory().toString());
            ps.setString(5, product.getDescription());
            return ps;
        }, keyHolder);

        product.setId(keyHolder.getKey().longValue());
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        String query = "select * from product where id=" + id;

        List<Product> products = jdbcTemplate.query(query,
                new BeanPropertyRowMapper(Product.class));

        if(!products.isEmpty()) {
            return Optional.ofNullable(products.get(0));
        }
        return Optional.empty();
    }
}
