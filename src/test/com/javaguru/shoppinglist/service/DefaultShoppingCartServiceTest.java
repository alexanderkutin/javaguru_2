package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.ShoppingCartRepository;
import com.javaguru.shoppinglist.service.validation.shoppingcart.ShoppingCartValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultShoppingCartServiceTest {

    @Mock
    ShoppingCartRepository repository;

    @Mock
    ShoppingCartValidationService validationService;

    @Captor
    private ArgumentCaptor<ShoppingCart> shoppingCartCaptor;

    @InjectMocks
    DefaultProductService victim;

    @Test
    public void shouldCreateCart() {





        verify(validationService).validate(shoppingCartCaptor.capture());


        verify(repository).insertCart(shoppingCartCaptor.capture());


    }

    @Test
    public void shouldAddProductToCart() {

    }





}