package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.mapper.ShoppingCartConverter;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DefaultShoppingCartService implements ShoppingCartService {
    static class FactoryHelper{
        ShoppingCart makeShoppingCart(String name){
            return new ShoppingCart(name);
        }
    }

    private ShoppingCartRepository shoppingCartRepository;
    private ShoppingCartValidationService validationService;
    private ProductService productService;
    private ShoppingCartConverter shoppingCartConverter;
    private FactoryHelper shoppingCartFactory;

    public DefaultShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService validationService, ProductService productService, ShoppingCartConverter shoppingCartConverter, FactoryHelper shoppingCartFactory) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.validationService = validationService;
        this.productService = productService;
        this.shoppingCartConverter = shoppingCartConverter;
        this.shoppingCartFactory = shoppingCartFactory;
    }

    @Autowired
    public DefaultShoppingCartService(ShoppingCartRepository shoppingCartRepository, ShoppingCartValidationService validationService, ProductService productService, ShoppingCartConverter shoppingCartConverter) {
        this(shoppingCartRepository, validationService, productService, shoppingCartConverter, new FactoryHelper());
    }


    @Override
    public Long create(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart newCart = shoppingCartFactory.makeShoppingCart(shoppingCartDTO.getName());
        newCart.setId(shoppingCartDTO.getId());
        validationService.validate(newCart);
        return shoppingCartRepository.saveCart(newCart);
    }

    @Override
    public boolean addProductToCart(String shoppingCartName, Long productId) {
        Product product = productService.findProductById(productId);
        ShoppingCart shoppingCart = shoppingCartRepository.findCartByName(shoppingCartName)
                .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + shoppingCartName + "\" does not exist"));

        boolean isInserted = shoppingCart.insertProduct(product);

        if(isInserted){
            shoppingCartRepository.saveCart(shoppingCart);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ShoppingCartDTO findByName(String name) {
        return shoppingCartRepository.findCartByName(name)
                .map(shoppingCartConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + name + "\" does not exist"));
    }

    @Override
    public ShoppingCartDTO findById(Long id) {
        return shoppingCartRepository.findCartById(id)
                .map(shoppingCartConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + id + "\" does not exist"));
    }

    @Override
    public void deleteByName(String name) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findCartByName(name);
        shoppingCartRepository.removeCart(optionalCart
                .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + name + "\" does not exist")));
    }

    @Override
    public void deleteById(Long id) {
        Optional<ShoppingCart> optionalCart = shoppingCartRepository.findCartById(id);
        shoppingCartRepository.removeCart(optionalCart
        .orElseThrow(() -> new NoSuchElementException("Shopping Cart \"" + id + "\" does not exist")));
    }
}
