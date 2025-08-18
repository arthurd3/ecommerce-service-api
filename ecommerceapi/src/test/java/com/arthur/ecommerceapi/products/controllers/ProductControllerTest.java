package com.arthur.ecommerceapi.products.controllers;

import com.arthur.ecommerceapi.products.controllers.mappers.ProductMapper;
import com.arthur.ecommerceapi.products.domain.models.Money;
import com.arthur.ecommerceapi.products.domain.models.Product;
import com.arthur.ecommerceapi.products.domain.models.enums.ProductCategory;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import com.arthur.ecommerceapi.products.exceptions.ProductNotFoundException;
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
    private Product savedReturnProduct;
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

        savedReturnProduct = ProductTestBuilder.aProduct()
                .withId(UUID.randomUUID())
                .withName("Product name")
                .withCategory(ProductCategory.ELECTRONICS)
                .withDescription("Eletronico Domestico")
                .withAvailableToDiscount(true)
                .withQuantity(100)
                .withPrice(new Money("100"))
            .buildDomain();

        responseProduct = ProductTestBuilder.aProduct()
                .withId(savedReturnProduct.getId())
                .withName(savedReturnProduct.getName())
                .withCategory(savedReturnProduct.getCategory())
                .withDescription(savedReturnProduct.getDescription())
                .withAvailableToDiscount(savedReturnProduct.getAvailableToDiscount())
                .withQuantity(savedReturnProduct.getQuantity())
                .withPrice(savedReturnProduct.getPrice())
            .buildResponseDTO();

    }

    @Nested
    @DisplayName("POST , /api/v1/product , Create Product")
    class createProductWithSuccess {

        @Test
        @DisplayName("Should create product with success and return 201 Created")
        void shouldCreateProductWithSuccess() throws Exception {

            when(mapper.toDomain(requestProduct)).thenReturn(productDomain);
            when(createProduct.create(productDomain)).thenReturn(savedReturnProduct);
            when(mapper.toDTO(savedReturnProduct)).thenReturn(responseProduct);

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
            verify(mapper).toDTO(savedReturnProduct);
        }

        @Test
        @DisplayName("Should create product with Error and return 400 Invalid request")
        void shouldCreateProductWithError400() throws Exception {

            var invalidRequest = new ProductRequestDTO(null , null , null , null , null , null);

            mockMvc.perform(post("/api/v1/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(invalidRequest)))
                    .andExpect(status().isBadRequest());

            verify(mapper , never()).toDomain(any(ProductRequestDTO.class));

            verify(createProduct, never()).create(any(Product.class));
        }

    }

    @Nested
    @DisplayName("DELETE , /api/v1/product/{uuid} , Delete Product ")
    class deleteProductWithSuccess {

        @Test
        @DisplayName("Should Delete Product with success")
        void shouldDeleteProductWithSuccess() throws Exception {
            final UUID idToDelete = UUID.randomUUID();

            doNothing().when(deleteProduct).delete(idToDelete);

            mockMvc.perform(delete("/api/v1/product/{uuid}" , idToDelete))
                    .andExpect(status().isOk());

            verify(deleteProduct , times(1)).delete(idToDelete);
        }

        @Test
        @DisplayName("Should Delete Product with 400 Error")
        void shouldDeleteProductWith400Error() throws Exception {
            final UUID invalidId = UUID.randomUUID();

            doThrow(new ProductNotFoundException("Product with :" + invalidId + " not exists!!"))
                    .when(deleteProduct).delete(invalidId);

            mockMvc.perform(delete("/api/v1/product/{uuid}" , invalidId))
                    .andExpect(status().isNotFound());

            verify(deleteProduct).delete(invalidId);
        }
    }

    @Nested
    @DisplayName("GET , /api/v1/product/{uuid} , Find by UUID Product ")
    class findByIdProductWithSuccess {

        @Test
        @DisplayName("")
        void shouldFindByIdProductWithSuccess() throws Exception {
            final UUID idToFind = savedReturnProduct.getId();

            when(findProduct.findById(idToFind)).thenReturn(savedReturnProduct);
            when(mapper.toDTO(savedReturnProduct)).thenReturn(responseProduct);

            mockMvc.perform(get("/api/v1/product/{uuid}" , idToFind)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(responseProduct.id().toString()))
                    .andExpect(jsonPath("$.name").value(responseProduct.name()))
                    .andExpect(jsonPath("$.formatedPrice").value(responseProduct.formatedPrice()))
                    .andExpect(jsonPath("$.description").value(responseProduct.description()))
                    .andExpect(jsonPath("$.quantity").value(responseProduct.quantity()));

            verify(mapper).toDTO(savedReturnProduct);
            verify(findProduct, times(1)).findById(idToFind);

        }
    }
    @Test
    void findById() {
    }

    @Test
    void update() {
    }
}