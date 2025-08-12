package com.arthur.ecommerceapi.products.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeleteProductTest {

    @Nested
    @DisplayName("Should existent product with success")
    class deleteProduct{

        @BeforeEach
        void setUp() {
        }

        @Test
        void delete() {
        }

    }
}