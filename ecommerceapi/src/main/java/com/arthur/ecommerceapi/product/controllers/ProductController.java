package com.arthur.ecommerceapi.product.controllers;

import com.arthur.ecommerceapi.product.dto.request.ProductRequestDTO;
import com.arthur.ecommerceapi.product.dto.response.ProductResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @ResponseStatus(OK)
    @PostMapping
    public ProductResponseDTO create(@RequestBody @Valid final ProductRequestDTO dto){

    }


}
