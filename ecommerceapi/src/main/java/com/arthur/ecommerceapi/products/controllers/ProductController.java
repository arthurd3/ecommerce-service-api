package com.arthur.ecommerceapi.products.controllers;

import com.arthur.ecommerceapi.products.controllers.mappers.ProductMapper;
import com.arthur.ecommerceapi.products.dtos.request.ProductRequestDTO;
import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;
import com.arthur.ecommerceapi.products.usecases.CreateProduct;
import com.arthur.ecommerceapi.products.usecases.DeleteProduct;
import com.arthur.ecommerceapi.products.usecases.FindProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductMapper mapper;
    private final CreateProduct createProduct;
    private final DeleteProduct deleteProduct;
    private final FindProduct findProduct;


    @ResponseStatus(OK)
    @PostMapping
    public ProductResponseDTO create(@RequestBody @Valid final ProductRequestDTO dto){
        return mapper.toDTO(createProduct.create(mapper.toDomain(dto)));
    }

    @ResponseStatus(NOT_FOUND)
    @PostMapping("/{uuid}")
    public void delete(@PathVariable final UUID uuid){
         deleteProduct.delete(uuid);
    }

    @ResponseStatus(OK)
    @GetMapping("/{uuid}")
    public ProductResponseDTO findById(@PathVariable final UUID uuid){
        return mapper.toDTO(findProduct.findById(uuid));
    }


}
