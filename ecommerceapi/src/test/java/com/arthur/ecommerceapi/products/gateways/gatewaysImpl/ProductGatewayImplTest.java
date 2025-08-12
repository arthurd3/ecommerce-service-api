package com.arthur.ecommerceapi.products.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
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
import org.springframework.dao.DataIntegrityViolationException;
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

    @Test
    void update() {
    }

    @Test
    void findEntityById() {
    }

    @Test
    void exists() {
    }
}