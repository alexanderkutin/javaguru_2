package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Profile("hibernate_v2")
@Transactional
public class ShoppingCartRepositoryHibernate implements ShoppingCartRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartRepositoryHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long saveCart(ShoppingCart shoppingCart) {
        sessionFactory.getCurrentSession().saveOrUpdate(shoppingCart);
        return shoppingCart.getId();
    }

    @Override
    public Optional<ShoppingCart> findCartById(Long id) {
        ShoppingCart shoppingCart = (ShoppingCart) sessionFactory.getCurrentSession()
                .createCriteria(ShoppingCart.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

        return Optional.ofNullable(shoppingCart);
    }

    @Override
    public Optional<ShoppingCart> findCartByName(String name) {
        ShoppingCart shoppingCart = (ShoppingCart) sessionFactory.getCurrentSession()
                .createCriteria(ShoppingCart.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        return Optional.ofNullable(shoppingCart);
    }

    @Override
    public void removeCart(ShoppingCart shoppingCart) {
        sessionFactory.getCurrentSession()
                .delete(shoppingCart);
    }
}
