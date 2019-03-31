package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("hibernate")
@Transactional
public class ProductRepositoryHibernate implements ProductRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        Product product = (Product) sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();

        return Optional.ofNullable(product);
    }

    @Override
    public Product insertProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        Product product = (Product) sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

        return Optional.ofNullable(product);
    }

    public List<Product> getAllProducts() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Product.class)
                .list();
    }

    public void delectProductById(Product product) {
        sessionFactory.getCurrentSession().delete(product);
    }
}
