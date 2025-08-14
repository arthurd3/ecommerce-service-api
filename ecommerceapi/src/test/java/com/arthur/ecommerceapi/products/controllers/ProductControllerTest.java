package com.arthur.ecommerceapi.products.controllers;

import com.arthur.ecommerceapi.products.controllers.mappers.ProductMapper;
import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import com.arthur.ecommerceapi.products.usecases.CreateProduct;
import com.arthur.ecommerceapi.products.usecases.DeleteProduct;
import com.arthur.ecommerceapi.products.usecases.FindProduct;
import com.arthur.ecommerceapi.products.usecases.UpdateProduct;
import com.arthur.ecommerceapi.testFactory.builders.ProductTestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductMapper mapper;

    @MockBean
    private CreateProduct createProduct;

    @MockBean
    private DeleteProduct deleteProduct;

    @MockBean
    private FindProduct findProduct;

    @MockBean
    private UpdateProduct updateProduct;

    private Product productDomain;
    private ProductRequestDTO requestProduct;
    private Product returnProduct;
    private ProductResponseDTO responseProduct;

    @BeforeEach
    void setUp() {

        requestProduct = ProductTestBuilder.aProduct()
                .withName("Product name")
                .withCategory(ProductCategory.ELECTRONICS)
                .withDescription("Eletronico Domestico")
                .withAvailableToDiscount(true)
                .withQuantity(100)
                .withPrice(new Money("100"))
            .buildRequestDTO();

        productDomain = ProductTestBuilder.aProduct()
                .withName("Product name")
                .withCategory(ProductCategory.ELECTRONICS)
                .withDescription("Eletronico Domestico")
                .withAvailableToDiscount(true)
                .withQuantity(100)
                .withPrice(new Money("100"))
            .buildDomain();

        returnProduct = ProductTestBuilder.aProduct()
                .withId(UUID.randomUUID())
                .withName("Product name")
                .withCategory(ProductCategory.ELECTRONICS)
                .withDescription("Eletronico Domestico")
                .withAvailableToDiscount(true)
                .withQuantity(100)
                .withPrice(new Money("100"))
            .buildDomain();

        responseProduct = ProductTestBuilder.aProduct()
                .withId(returnProduct.getId())
                .withName(returnProduct.getName())
                .withCategory(returnProduct.getCategory())
                .withDescription(returnProduct.getDescription())
                .withAvailableToDiscount(returnProduct.getAvailableToDiscount())
                .withQuantity(returnProduct.getQuantity())
                .withPrice(returnProduct.getPrice())
            .buildResponseDTO();

    }

    @Nested
    @DisplayName("POST , /api/v1/product , Create Product")
    class createProductWithSuccess {

        @Test
        @DisplayName("Should create product with success and return 201 Created")
        void shouldCreateProductWithSuccess() throws Exception {

            when(mapper.toDomain(requestProduct)).thenReturn(productDomain);
            when(createProduct.create(productDomain)).thenReturn(returnProduct);
            when(mapper.toDTO(returnProduct)).thenReturn(responseProduct);

            mockMvc.perform(post("/api/v1/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(requestProduct)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(responseProduct.id().toString()))
                    .andExpect(jsonPath("$.name").value(responseProduct.name()))
                    .andExpect(jsonPath("$.formatedPrice").value(responseProduct.formatedPrice()))
                    .andExpect(jsonPath("$.description").value(responseProduct.description()))
                    .andExpect(jsonPath("$.quantity").value(responseProduct.quantity()));


            verify(mapper).toDomain(requestProduct);
            verify(createProduct).create(productDomain);
            verify(mapper).toDTO(returnProduct);
        }

    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}