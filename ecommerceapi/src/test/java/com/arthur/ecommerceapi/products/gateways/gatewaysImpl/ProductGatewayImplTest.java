package com.arthur.ecommerceapi.products.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.exceptions.ProductNotFoundException;
import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import com.arthur.ecommerceapi.products.repositories.ProductRepository;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import jakarta.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductGatewayImplTest {

    @Autowired
    private ProductGateway productGateway;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private ProductEntity originalProduct;

    @BeforeEach
    void setUp() {
        originalProduct = ProductTestBuilder.aProduct()
                .withCategory(ProductCategory.ELECTRONICS)
                .withDescription("Eletronico Domestico")
                .withAvailableToDiscount(true)
                .withQuantity(100)
                .withPrice(new Money("100"))
                .buildEntity();

        originalProduct = productRepository.save(originalProduct);
        entityManager.flush();
    }

    @Nested
    @DisplayName("Delete Product")
    class deleteProductTest {

        @Test
        void shouldDeleteProductWithSuccess() {

            final UUID productId = originalProduct.getId();
            productGateway.delete(productId);

            entityManager.flush();

            assertFalse(productRepository.findById(productId).isPresent());
        }

        @Test
        void shouldNotDeleteProductWithNotFound() {
            final UUID nonExistentId = UUID.randomUUID();
            long countBeforeDelete = productRepository.count();

            assertDoesNotThrow(() -> {
                productGateway.delete(nonExistentId);
                entityManager.flush();
            });

            assertEquals(countBeforeDelete, productRepository.count());
        }

    }

    @Nested
    @DisplayName("Update Product")
    class updateProductTest {

        @Test
        @DisplayName("Should Update Product with success")
        void shouldUpdateProductWithSuccess() {
            Product productToChange = ProductTestBuilder.aProduct()
                    .withId(originalProduct.getId())
                    .withDescription("Eletronico")
                    .withAvailableToDiscount(false)
                    .withPrice(new Money("50"))
                    .buildDomain();

            productGateway.update(productToChange);

            ProductEntity changedProduct = productRepository.findById(productToChange.getId())
                    .orElseThrow();

            assertEquals(changedProduct.getId(), originalProduct.getId());
            assertEquals(changedProduct.getDescription(), productToChange.getDescription());
            assertEquals(changedProduct.getAvailableToDiscount(), productToChange.getAvailableToDiscount());
            assertEquals(changedProduct.getPrice(), productToChange.getPrice().getValue());
            assertEquals(changedProduct.getQuantity(), originalProduct.getQuantity());
        }

        @Test
        @DisplayName("Should not update With Success")
        void shouldNotUpdateProductWithSuccess() {
            Product productToChange = ProductTestBuilder.aProduct()
                    .withId(UUID.randomUUID())
                    .withDescription("Eletronico")
                    .withAvailableToDiscount(false)
                    .withPrice(new Money("50"))
                    .buildDomain();

            assertThrows(ProductNotFoundException.class,
                    () -> productGateway.update(productToChange));
        }

    }

    @Test
    void findEntityById() {
    }

    @Test
    void exists() {
    }
}