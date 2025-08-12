package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.exceptions.ProductNotFoundException;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductTest {

    @InjectMocks
    private DeleteProduct deleteProduct;

    @Mock
    private ProductGateway productGateway;

    @Nested
    @DisplayName("Should existent product with success")
    class deleteProduct{

        private Product createdProduct;

        @BeforeEach
        void setUp() {
            createdProduct = ProductTestBuilder.aProduct()
                    .withCategory(ProductCategory.ELECTRONICS)
                    .withId(UUID.randomUUID())
                    .withDescription("Eletronico Domestico")
                    .withAvailableToDiscount(true)
                    .withQuantity(100)
                    .withPrice(new Money("100"))
                    .buildDomain();
        }

        @Test
        @DisplayName("Should delete when exists with success")
        void shouldDeleteWhenExists() {
            when(productGateway.exists(createdProduct.getId())).thenReturn(true);

            deleteProduct.delete(createdProduct.getId());

            verify(productGateway , times(1)).delete(createdProduct.getId());
        }

        @Test
        @DisplayName("Should throw product not found Exception")
        void shouldThrowProductNotFoundException() {
            when(productGateway.exists(createdProduct.getId())).thenReturn(false);

            assertThrows(ProductNotFoundException.class , () ->
                    deleteProduct.delete(createdProduct.getId()));
            
            verify(productGateway , times(0)).delete(createdProduct.getId());
        }
    }
}