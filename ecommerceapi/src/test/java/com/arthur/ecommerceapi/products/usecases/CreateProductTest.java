package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.products.repositories.ProductRepository;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductTest {

    @InjectMocks
    private CreateProduct createProduct;

    @Mock
    private ProductGateway productGateway;

    @Nested
    @DisplayName("Create Product with Success")
    class CreateProductWithSuccess {

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
        @DisplayName("Should create product with success")
        void shouldCreateProductWithSuccess() {

            when(productGateway.create(createdProduct)).thenReturn(createdProduct);

            Product successCreated = createProduct.create(createdProduct);

            assertEquals(createdProduct, successCreated);
            assertEquals(createdProduct.getCategory(), successCreated.getCategory());
            assertEquals(createdProduct.getDescription(), successCreated.getDescription());
            assertEquals(createdProduct.getAvailableToDiscount(), successCreated.getAvailableToDiscount());
            assertEquals(createdProduct.getQuantity(), successCreated.getQuantity());
            assertEquals(createdProduct.getPrice(), successCreated.getPrice());

        }
    }
}