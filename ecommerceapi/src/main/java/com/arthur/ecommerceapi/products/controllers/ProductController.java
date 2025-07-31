package com.arthur.ecommerceapi.products.controllers;

import com.arthur.ecommerceapi.products.controllers.mappers.ProductMapper;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import com.arthur.ecommerceapi.products.usecases.CreateProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper mapper;
    private final CreateProduct createProduct;

    @ResponseStatus(OK)
    @PostMapping
    public ProductResponseDTO create(@RequestBody @Valid final ProductRequestDTO dto){
        return mapper.toDTO(createProduct.create(mapper.toDomain(dto)));
    }


}
