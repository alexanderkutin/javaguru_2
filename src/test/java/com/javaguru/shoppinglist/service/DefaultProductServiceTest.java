package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Category;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepositoryMemory;
import com.javaguru.shoppinglist.service.validation.ObjectValidator;
import com.javaguru.shoppinglist.service.validation.product.ProductValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceTest {

    private final Long TEST_ID = 1001L;

    @Mock
    private ProductRepositoryMemory repository;

    @Mock
    private ProductValidationService validationService;

    @Mock
    private ObjectValidator validator;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Captor
    private ArgumentCaptor<Long> idCaptor;

    @InjectMocks
    DefaultProductService victim;


    @Test
    public void shouldCreateProduct() {
        Product product = product();

        when(repository.insertProduct(product)).thenReturn(product);

        Long result = victim.create(product);

        verify(validationService).validate(productCaptor.capture());
        Product captorResult = productCaptor.getValue();

        assertThat(captorResult).isEqualTo(product);
        assertThat(product.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(TEST_ID)).thenReturn(Optional.ofNullable(product()));

        Product result = victim.findBy(TEST_ID);

        verify(validator).validate(idCaptor.capture());

        assertThat(result).isEqualTo(product());
        assertThat(idCaptor.getValue()).isEqualTo(TEST_ID);
    }

    @Test
    public void shouldThrowNoSuchElementException() {
        when(repository.findProductById(TEST_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findBy(TEST_ID))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Product with ID:" + TEST_ID + " does not exist");
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setCategory(Category.TOOL);
        product.setPrice(new BigDecimal(100));
        product.setDiscount(new BigDecimal(50));
        product.setId(TEST_ID);
        product.setDescription("TEST_DESCRIPTION");
        return product;
    }
}