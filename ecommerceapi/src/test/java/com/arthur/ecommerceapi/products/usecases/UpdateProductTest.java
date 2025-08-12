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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateProductTest {

    @InjectMocks
    private UpdateProduct updateProduct;

    @Mock
    private ProductGateway productGateway;

    @Nested
    @DisplayName("Should Update product with success")
    class updateProduct{

        private Product toUpdateProduct;

        @BeforeEach
        void setUp() {
            final UUID id = UUID.randomUUID();

            toUpdateProduct = ProductTestBuilder.aProduct()
                    .withId(id)
                    .withName("Product 1")
                    .withAvailableToDiscount(true)
                    .withCategory(ProductCategory.ELECTRONICS)
                    .withDescription("Product 1 from Electronics")
                    .withQuantity(1000)
                    .withPrice(new Money("199"))
                    .buildDomain();

        }

        @Test
        @DisplayName("Should Update product with success")
        void shouldUpdateProductWithSuccess() {

            when(productGateway.update(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

            Product updatedProduct = updateProduct.update(toUpdateProduct);

            assertEquals(toUpdateProduct.getId(), updatedProduct.getId());
            assertEquals(toUpdateProduct.getName(), updatedProduct.getName());
            assertEquals(toUpdateProduct.getAvailableToDiscount(), updatedProduct.getAvailableToDiscount());
            assertEquals(toUpdateProduct.getCategory(), updatedProduct.getCategory());
            assertEquals(toUpdateProduct.getDescription(), updatedProduct.getDescription());
            assertEquals(toUpdateProduct.getQuantity(), updatedProduct.getQuantity());
            assertEquals(toUpdateProduct.getPrice(), updatedProduct.getPrice());

        }


        @Test
        @DisplayName("Should Update product with success")
        void shouldThrow() {
            var erroUpdateProduct = ProductTestBuilder.aProduct()
                    .withId(UUID.randomUUID())
                    .withName("Product 1")
                    .withAvailableToDiscount(true)
                    .withCategory(ProductCategory.ELECTRONICS)
                    .withDescription("Product 1 from Electronics")
                    .withQuantity(1000)
                    .withPrice(new Money("199"))
                    .buildDomain();

            when(productGateway.update(erroUpdateProduct))
                    .thenThrow(new ProductNotFoundException("Product with "+ erroUpdateProduct.getId() +" not found!"));

            assertThrows(ProductNotFoundException.class,
                    () -> updateProduct.update(erroUpdateProduct));
        }
    }


}