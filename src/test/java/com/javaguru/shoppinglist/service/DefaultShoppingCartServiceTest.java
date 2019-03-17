package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
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


import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultShoppingCartServiceTest {

    private final String TEST_NAME = "TEST_NAME";

    @Mock
    ShoppingCartRepository repository;

    @Mock
    ShoppingCartValidationService validationService;

    @Mock
    DefaultShoppingCartService.FactoryHelper mockShopCartFactory;

    @Captor
    private ArgumentCaptor<ShoppingCart> shoppingCartCaptor;

    @InjectMocks
    DefaultShoppingCartService victim;

    @Test
    public void shouldCreateCart() {
        when(mockShopCartFactory.makeShoppingCart(TEST_NAME)).thenReturn(shoppingCart());

        victim.create(TEST_NAME);

        verify(validationService).validate(shoppingCartCaptor.capture());
        assertThat(shoppingCartCaptor.getValue()).isEqualTo(shoppingCart());

        verify(repository).insertCart(shoppingCartCaptor.capture());
        assertThat(shoppingCartCaptor.getValue()).isEqualTo(shoppingCart());
    }

    @Test
    public void shouldAddProductToCart() {
        ShoppingCart shoppingCartMock = mock(ShoppingCart.class);
        Product productMock = mock(Product.class);

        when(shoppingCartMock.insertProduct(productMock)).thenReturn(true);

        Boolean result = victim.addProductToCart(shoppingCartMock, productMock);

        assertEquals(result, true);
    }

    @Test
    public void shouldFindByName() {
        when(repository.findCartByName(TEST_NAME)).thenReturn(Optional.ofNullable(shoppingCart()));

        ShoppingCart result = victim.findByName(TEST_NAME);
        assertThat(result).isEqualTo(shoppingCart());
    }

    @Test
    public void shouldThrowNoSuchElementException() {
        when(repository.findCartByName(TEST_NAME)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findByName(TEST_NAME))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Shopping Cart \"" + TEST_NAME + "\" does not exist");
    }

    @Test
    public void shouldDeleteByName() {
        when(repository.findCartByName(TEST_NAME)).thenReturn(Optional.ofNullable(shoppingCart()));
        when(repository.removeCart(shoppingCart())).thenReturn(true);

        Boolean result = victim.deleteByName(TEST_NAME);
        verify(repository).removeCart(shoppingCartCaptor.capture());

        assertEquals(result, true);
        assertThat(shoppingCartCaptor.getValue()).isEqualTo(shoppingCart());
    }

    private ShoppingCart shoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(TEST_NAME);
        return shoppingCart;
    }
}