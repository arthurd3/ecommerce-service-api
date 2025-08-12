package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindProductEntityTest {

    @InjectMocks
    private FindProductEntity findProductEntity;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
    }
}