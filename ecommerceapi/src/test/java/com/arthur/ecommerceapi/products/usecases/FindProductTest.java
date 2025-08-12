package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductTest {

    @InjectMocks
    private FindProduct findProduct;

    @Mock
    private ProductGateway productGateway;

    @Nested
    @DisplayName("Should find product with success")
    class FindProductWithSuccess {

        private Product foundProduct;

        @BeforeEach
        void setUp() {
            foundProduct = ProductTestBuilder.aProduct()
                    .withCategory(ProductCategory.ELECTRONICS)
                    .withId(UUID.randomUUID())
                    .withDescription("Eletronico Domestico")
                    .withAvailableToDiscount(true)
                    .withQuantity(100)
                    .withPrice(new Money("100"))
                    .buildDomain();
        }

        @Test
        void shouldFindProductById() {
            when(productGateway.findById(foundProduct.getId())).thenReturn(foundProduct);

            Product product = findProduct.findById(foundProduct.getId());

            assertEquals(foundProduct, product);
            assertEquals(foundProduct.getCategory(), product.getCategory());
            assertEquals(foundProduct.getDescription(), product.getDescription());
            assertEquals(foundProduct.getAvailableToDiscount(), product.getAvailableToDiscount());
            assertEquals(foundProduct.getQuantity(), product.getQuantity());
            assertEquals(foundProduct.getPrice(), product.getPrice());
            assertEquals(foundProduct.getId(), product.getId());
        }
    }
}